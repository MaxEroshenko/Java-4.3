package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue implements Comparable<Issue> {
    private int id;
    private boolean isOpen;
    private String author;
    private int whenOpened;
    private Set<String> issueLabels = new HashSet<>();
    private Set<String> issueAssignees = new HashSet<>();

    @Override
    public int compareTo(Issue o) {
        return whenOpened - o.whenOpened;
    }
}
