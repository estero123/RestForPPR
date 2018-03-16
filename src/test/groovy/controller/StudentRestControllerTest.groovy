package controller

import com.tylkowski.controller.StudentRestController
import com.tylkowski.entity.Student
import com.tylkowski.repository.StudentRepository
import com.tylkowski.service.StudentServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class StudentRestControllerTest extends Specification {
    def studentRepository
    def studentService
    def studentRestController
    RestTemplate restTemplate


    def setup() {
        given:
        restTemplate = new RestTemplate()
        studentRepository = Stub(StudentRepository.class)
        studentService = new StudentServiceImpl(studentRepository)
        studentRestController = new StudentRestController(studentService)
    }

    def "should return list of all students"() {
        when:
        ResponseEntity<Iterable<Student>> response = restTemplate.getForEntity("http://localhost:8080/api/students/", Iterable.class)
        then:
        response.getStatusCode() == HttpStatus.OK
    }

    def "should return one student"() {
        when:
        ResponseEntity<Optional> response = restTemplate.getForEntity("http://localhost:8080/api/students/1", Optional.class)
        then:
        response.getStatusCode() == HttpStatus.OK
    }
}
