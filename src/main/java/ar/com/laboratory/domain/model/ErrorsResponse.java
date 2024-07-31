package ar.com.laboratory.domain.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Builder
@Schema(description = "Errors response model")
public class ErrorsResponse extends BaseErrorResponse {

    @Schema(description = "List of error messages", example = "[\"Error 1\", \"Error 2\"]")
    private List<String> errors;

    @Schema(description = "Error message", example = "Invalid request")
    private String code;

    @Schema(description = "Error status", example = "500")
    private String status;
}