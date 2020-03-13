package ir.maktab.finalproject.onlinequizapplication.mapper;

import ir.maktab.finalproject.onlinequizapplication.dto.CourseCreateDTO;
import ir.maktab.finalproject.onlinequizapplication.enumeration.CourseStatus;
import ir.maktab.finalproject.onlinequizapplication.model.Course;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class CourseDtoToCourseMapper {
    public static Course mapper(CourseCreateDTO courseCreateDTO) throws ParseException {
        return new Course(
                null,
                courseCreateDTO.getCourseCode(),
                courseCreateDTO.getTitle(),
                new SimpleDateFormat("yyyy-mm-dd").parse(courseCreateDTO.getStartDate()),
                new SimpleDateFormat("yyyy-mm-dd").parse(courseCreateDTO.getEndDate()),
                CourseStatus.valueOf(courseCreateDTO.getCourseStatus())
        );
    }
}

