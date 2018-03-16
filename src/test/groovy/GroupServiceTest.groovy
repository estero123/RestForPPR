import com.tylkowski.entity.Group
import com.tylkowski.repository.GroupRepository
import com.tylkowski.service.GroupServiceImpl
import spock.lang.Specification

class GroupServiceTest extends Specification {
    def groupRepository
    def groupService

    def setup() {
        given:
        groupRepository = Stub(GroupRepository.class)
        groupService = new GroupServiceImpl(groupRepository)
    }

    def "should find group"() {
        when:
        groupRepository.findById(_) >> Optional.of(new Group("Test"))
        def group = groupService.findById(1)
        then:
        group.get().getGroupName() == "Test"
    }

    def "should save group"() {
        when:
        groupRepository.save(_) >> new Group("Test")
        def group = groupService.save(new Group("Test"))
        then:
        group.getGroupName() == "Test"
    }

    def "should list all groups"() {
        when:
        def groupList = new ArrayList<>()
        groupRepository.findAll() >> groupList
        then:
        groupService.findAll().equals(groupList)
    }


}
