package ar.com.laboratory.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Schema(description = "Error response model")
public class ErrorResponse extends BaseErrorResponse {

    @Schema(description = "Error message", example = "Invalid request")
    private String message;

    @Schema(description = "Error code", example = "500")
    private String code;

    @Schema(description = "Error status", example = "500")
    private String status;
}