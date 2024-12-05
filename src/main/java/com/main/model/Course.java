package com.main.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable
public class Course {

	@Column(name = "course_id")
	Integer courseId;

	@Column(name = "course_name")
	String courseName;
}
