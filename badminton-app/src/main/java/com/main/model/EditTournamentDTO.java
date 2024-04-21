package com.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditTournamentDTO {
    private EditTournamentModel editTournamentModel;
    private CreateTournamentModel createTournamentModel;
}
