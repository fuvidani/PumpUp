-- Exercise
MERGE INTO exercise
VALUES (1, 'liegestuetz', 'eine der besten uebungen ueberhaupt', 9.0, '', FALSE);
MERGE INTO gif VALUES (1, 1, 'menshealth');
MERGE INTO gif VALUES (2, 1, 'youtube');

-- Training
MERGE INTO TrainingsPlan VALUES (1, NULL, 'Testtraining1', 'Testtrainingbeschreibung', FALSE);
MERGE INTO TrainingsPlan VALUES (2, NULL, 'Testtraining2', 'Testtrainingbeschreibung', FALSE);

MERGE INTO TrainingsSession VALUES (1, 1, 'Session 1', NULL, FALSE);
MERGE INTO TrainingsSession VALUES (2, 1, 'Session 2', NULL, FALSE);

MERGE INTO ExerciseSet VALUES (1, 1, NULL, 10, 1, 1, FALSE);
MERGE INTO ExerciseSet VALUES (2, 1, NULL, 15, 2, 1, FALSE);
MERGE INTO ExerciseSet VALUES (3, 1, NULL, 5, 3, 1, FALSE);

MERGE INTO ExerciseSet VALUES (4, 1, NULL, 20, 1, 2, FALSE);
MERGE INTO ExerciseSet VALUES (5, 1, NULL, 9, 2, 2, FALSE);
MERGE INTO ExerciseSet VALUES (6, 1, NULL, 8, 3, 2, FALSE);

ALTER SEQUENCE seq_TP RESTART WITH 2;
ALTER SEQUENCE seq_TPType RESTART WITH 3;
ALTER SEQUENCE seq_TS RESTART WITH 4;
ALTER SEQUENCE seq_ES RESTART WITH 7;
ALTER SEQUENCE exercisesequence RESTART WITH 2;
ALTER SEQUENCE gifsequence RESTART WITH 3;
