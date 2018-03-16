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


    def "should find student"() {
        when:
        studentRepository.findById(_) >> Optional.of(new Student("Test", "Test"))
        def student = studentService.findById(1)
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

    def "should list all students"() {
        when:
        def studentList = new ArrayList<>()
        studentRepository.findAll() >> studentList
        then:
        studentService.findAll() == studentList
    }


}
