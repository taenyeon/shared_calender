package com.project.shared_calender.domain.user.constant;

public interface AttributeConverter<SERVER_DATA, DB_DATA> {
    public DB_DATA convertToDatabaseColumn (SERVER_DATA serverData);
    
    public SERVER_DATA convertToEntityAttribute (DB_DATA dbData);
}
