package com.employee.service.impl;

import com.employee.api.request.CreateDayOffRequest;
import com.employee.api.response.ResponseHandler;
import com.employee.domain.DayOff;
import com.employee.domain.DayOffRequest;
import com.employee.domain.Employee;
import com.employee.enums.Status;
import com.employee.mapper.DayoffRequestMapper;
import com.employee.repository.DayOffRepository;
import com.employee.repository.DayoffRequestRepository;
import com.employee.service.DayOffQueryService;
import com.employee.service.DayOffRequestCommandService;
import com.employee.service.DayOffRequestQueryService;
import com.employee.service.EmployeeQueryService;
import liquibase.pro.packaged.T;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@RequiredArgsConstructor
public class DayOffRequestCommandServiceImpl implements DayOffRequestCommandService {


    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static final String HOLIDAY_API = "https://jsonblob.com/api/jsonBlob/9f8c8738-e70d-11eb-9ebe-f9437bbd74b4";

    private final EmployeeQueryService employeeQueryService;
    private final DayOffQueryService dayOffQueryService;
    private final DayoffRequestRepository dayoffRequestRepository;
    private final DayoffRequestMapper dayoffRequestMapper;
    private final DayOffRequestQueryService dayOffRequestQueryService;
    private final DayOffRepository dayOffRepository;
    private final LocalizationService localizationService;

    @Override
    public ResponseEntity<T> createDayOffRequest(CreateDayOffRequest createDayOffRequest) {
        Date startDate = null;
        Date finishDate = null;

        Employee employee = this.employeeQueryService.getEmployeeById(createDayOffRequest.getEmployeeId());
        if (employee == null) {
            return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("employee.not.found"), HttpStatus.OK, null);
        }

        DayOff dayOff = this.dayOffQueryService.getDayOffByEmployeeId(createDayOffRequest.getEmployeeId());

        try {
            startDate = format.parse(String.valueOf(createDayOffRequest.getDayOffStartDate()));
            finishDate = format.parse(String.valueOf(createDayOffRequest.getDayOffFinishDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int workDays = this.getWorkingDaysBetweenTwoDatesAsHolidays(startDate, finishDate);

        List<DayOffRequest> dayOffRequestList = this.dayOffRequestQueryService.getDayOffRequestList(employee.getId());

        long yearsBetween = ChronoUnit.YEARS.between(employee.getWorkStartDate(), createDayOffRequest.getDayOffStartDate());

        int totalDays = 0;
        for (DayOffRequest dayOffRequest : dayOffRequestList) {
            totalDays += dayOffRequest.getWorkDays();
        }

        if (yearsBetween < 1 && totalDays + workDays > 5) {
            return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("can.not.take.day.off.more.five.days"), HttpStatus.OK, null);
        }

        if (!(yearsBetween < 1) && totalDays + workDays > dayOff.getAnnualPermit()) {
            return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("can.not.take.day.off"), HttpStatus.OK, null);
        }

        DayOffRequest dayoffRequest = this.dayoffRequestMapper.toEntity(createDayOffRequest);
        dayoffRequest.setStatus(Status.PENDING);
        dayoffRequest.setWorkDays(workDays);
        dayoffRequest.setApprovedEmployee(employee.getManager());
        this.dayoffRequestRepository.save(dayoffRequest);

        return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("created.successfully"), HttpStatus.OK, this.dayoffRequestRepository.save(dayoffRequest).getId());

    }

    @Override
    public ResponseEntity<T> approveDayOffRequest(Long dayOffRequestId) {

        DayOffRequest dayoffRequest = this.dayOffRequestQueryService.getDayOffRequest(dayOffRequestId);
        DayOff dayOff = this.dayOffQueryService.getDayOffByEmployeeId(dayoffRequest.getEmployee().getId());

        int lastAnnualPermit = dayOff.getAnnualPermit() - dayoffRequest.getWorkDays();

        dayOff.setAnnualPermit(lastAnnualPermit);
        dayoffRequest.setStatus(Status.APPROVED);
        this.dayOffRepository.save(dayOff);
        dayoffRequest = this.dayoffRequestRepository.save(dayoffRequest);

        return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("day.off.request.approved.successfully"), HttpStatus.OK, dayoffRequest.getId());
    }

    @Override
    public ResponseEntity<T> rejectedDayOffRequest(Long dayOffRequestId) {
        DayOffRequest dayoffRequest = this.dayOffRequestQueryService.getDayOffRequest(dayOffRequestId);
        dayoffRequest = this.dayoffRequestRepository.save(dayoffRequest);

        return ResponseHandler.generateResponse(this.localizationService.getLocalizationMessage("day.off.request.rejected.successfully"), HttpStatus.OK, dayoffRequest.getId());
    }

    public int getWorkingDaysBetweenTwoDatesAsWeekends(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int workDays = 1;
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }
        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }
        do {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

        return workDays;
    }

    public int getWorkingDaysBetweenTwoDatesAsHolidays(Date startDate, Date endDate) {
        JSONObject json = null;
        int specialDaysCount = 0;
        try {
            json = readJsonFromUrl(HOLIDAY_API);
            for (Object date : json.getJSONArray("holidays")) {
                String specialDate = ((JSONObject) date).getJSONObject("date").getString("iso");
                boolean aBoolean = isDateInBetweenIncludingEndPoints(startDate, endDate, format.parse(specialDate));
                if (aBoolean)
                    specialDaysCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int workDaysWithoutWeekends = this.getWorkingDaysBetweenTwoDatesAsWeekends(startDate, endDate);

        return workDaysWithoutWeekends - specialDaysCount;
    }

    public static boolean isDateInBetweenIncludingEndPoints(final Date min, final Date max, final Date date) {
        return !(date.before(min) || date.after(max));
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
