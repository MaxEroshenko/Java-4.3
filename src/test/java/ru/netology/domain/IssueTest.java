package ru.netology.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class IssueTest {
    private Issue first = new Issue(1, true, "Author1", 1, new HashSet<>(List.of("label1", "label2")), new HashSet<>(List.of("assignee1", "assignee2")));
    private Issue second = new Issue(2, false, "Author2", 5, new HashSet<>(List.of("label3", "label4")), new HashSet<>(List.of("assignee7", "assignee8")));
    private Issue third = new Issue(3, true, "Author1", 3, new HashSet<>(List.of("label3", "label4")), new HashSet<>(List.of("assignee4", "assignee1")));

    @Test
    public void shouldSortByTimeOpened() {
        Issue[] expected = new Issue[]{first, third, second};
        Issue[] actual = new Issue[]{first, second, third};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}
