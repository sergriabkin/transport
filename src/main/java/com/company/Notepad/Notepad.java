package com.company.Notepad;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notepad {
    private List<Note> notes;

    public Notepad() {
        this.notes = new ArrayList<>();
    }

    public void addNote(String text, LocalDate date) {
        if (!notes.isEmpty()) {
            for (Note note : notes) {
                if (note.getDate() == date) {
                    note.list.add(text);
                    return;
                }
            }
        }
        Note result = new Note(date);
        result.addNote(text);
        notes.add(result);
    }

    public List<String> getAllNotes() {
        return notes.stream()
                .flatMap(n -> n.getList().stream())
                .collect(Collectors.toList());
    }

    private class Note {
        private List<String> list;
        private LocalDate date;

        Note(LocalDate date) {
            this.list = new ArrayList<>();
            this.date = date;
        }

        LocalDate getDate() {
            return date;
        }

        List<String> getList() {
            return list;
        }

        void addNote(String text) {
            list.add(text);
        }
    }

}
