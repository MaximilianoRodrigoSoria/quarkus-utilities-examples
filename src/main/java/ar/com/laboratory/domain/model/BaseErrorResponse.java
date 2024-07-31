package ar.com.laboratory.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseErrorResponse {

    @Schema(description = "Status of the error response", example = "error")
    private String status;

    @Schema(description = "Error code", example = "400")
    private String code;
}