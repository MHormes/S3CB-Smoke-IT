package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.BoxDTO;

import java.util.ArrayList;
import java.util.List;

public class FakeDataSource {

    List<BoxDTO> fakeBoxList = new ArrayList<>();

    public FakeDataSource(){
        fakeBoxList.add(new BoxDTO(1, "Roll-Kit", 4.99, "Papers, Blunts, Joint-Tube", "The perfect small kit for every smoker out there"));
        fakeBoxList.add(new BoxDTO(2, "Supply-Kit", 9.99, "Crusher, Pipe, Ashtray", "Ready for a small surprise every time? Includes Roll-Kit"));
        fakeBoxList.add(new BoxDTO(3, "Smoke-Kit", 16.99, "Papers, Blunts, Joint-Tube", "The perfect small kit for every"));
    }

}
