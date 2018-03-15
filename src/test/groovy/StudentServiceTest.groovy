import com.tylkowski.entity.Student
import com.tylkowski.repository.StudentRepository
import com.tylkowski.service.StudentServiceImpl
import spock.lang.Specification

class StudentServiceTest extends Specification {

    def studentRepository
    def studentService


    def setup() {
        given:
        studentRepository = Stub(StudentRepository.class)
        studentService = new StudentServiceImpl(studentRepository)
    }


    def "should return student"() {
        when:
        studentRepository.findById(_) >> Optional.of(new Student("Test", "Test"))
        def student = studentService.findOne(1)
        then:
        student.get().getFirstName() == "Test"
        student.get().getLastName() == "Test"
    }

    def "should save student"() {
        when:
        studentRepository.save(_) >> new Student("Test", "Test")
        def student = studentService.save(new Student("Test", "Test"))
        then:
        student.getFirstName() == "Test"
        student.getLastName() == "Test"
    }


}
