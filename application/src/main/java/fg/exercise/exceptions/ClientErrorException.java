package fg.exercise.exceptions;

import fg.exercise.models.dtos.ExceptionDto;


public abstract class ClientErrorException extends RuntimeException {

    protected long code;
    protected String description;


    public ExceptionDto toDto() {
        ExceptionDto dto = new ExceptionDto();
        dto.setCode(code);
        dto.setDescription(description);
        return dto;
    }
}
