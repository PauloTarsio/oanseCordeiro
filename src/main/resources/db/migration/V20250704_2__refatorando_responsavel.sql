CREATE TABLE Oansista_Responsavel (
    oansista_id INTEGER NOT NULL REFERENCES Oansista(id) ON DELETE CASCADE,
    responsavel_id INTEGER NOT NULL REFERENCES Responsavel(id) ON DELETE CASCADE,
    PRIMARY KEY (oansista_id, responsavel_id)
);

