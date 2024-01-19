package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Credential {
    private int id;
    @NonNull
    private String nombre;
    @NonNull
    private String password;
}
