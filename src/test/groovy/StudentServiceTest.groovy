import com.tylkowski.controller.StudentRestController
import com.tylkowski.entity.Student
import com.tylkowski.repository.StudentRepository
import com.tylkowski.service.StudentService
import com.tylkowski.service.StudentServiceImpl
import spock.lang.Specification


class StudentServiceTest extends Specification {

    def studentRepository
    def studentService


    def setup() {
        studentRepository = Stub(StudentRepository.class)
        studentService = new StudentServiceImpl(studentRepository)
    }


  def "should return a optional student"() {

      when:
        studentRepository.findById(_) >> Optional.of(new Student("Test", "Test"))
        def student2 = studentService.findOne(10)
      then:
        student2.get().getFirstName() == "Test"
  }

    



}
