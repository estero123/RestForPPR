import com.tylkowski.controller.StudentRestController
import com.tylkowski.entity.Student
import com.tylkowski.repository.StudentRepository
import com.tylkowski.service.StudentService
import com.tylkowski.service.StudentServiceImpl
import spock.lang.Specification


class StudentServiceTest extends Specification {

  def "should return a optional student"() {
      given:
        Optional<Student> student = Optional.of(new Student("Test", "Test"))
        StudentRepository studentRepository = Stub(StudentRepository.class)
      and:
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository)
//        studentService = Stub()
        studentService.findOne(10) >> student
      when:
        def student2 = studentService.findOne(10).get()
      then:
        student2.getFirstName() == "Test"
        student2.getLastName() == "Test"
  }

}
