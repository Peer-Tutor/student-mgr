package com.peertutor.StudentMgr.service.mapper;

import com.peertutor.StudentMgr.model.Student;
import com.peertutor.StudentMgr.service.dto.StudentDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link com.peertutor.StudentMgr.model.Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {

    Student toEntity(StudentDTO accountDTO);

    StudentDTO toDto(Student account);

    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student account = new Student();
        account.setId(id);
        return account;
    }
}
