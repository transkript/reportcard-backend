package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.Student;
import java.util.List;

public interface StudentService {
    
    EntityResponse addStudent( StudentDto studentDto);

    
    List<StudentDto> getAllDto();

    
    StudentDto getDto( Long id);

    void delete( Long id);

    
    Student getEntity( Long id);

    
    EntityResponse update( StudentDto studentDto);
}
