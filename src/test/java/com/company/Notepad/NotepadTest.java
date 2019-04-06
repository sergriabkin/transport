package com.company.Notepad;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class NotepadTest {

    @Test
    public void shouldReturnText() {
        Notepad notepad = new Notepad();
        notepad.addNote("Some text", LocalDate.now());
        String actual = notepad.getAllNotes().get(0);
        String expected = "Some text";
        assertEquals(expected, actual);
    }
}
