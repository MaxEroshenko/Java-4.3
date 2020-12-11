package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    private List<Issue> filterBy(Predicate<Issue> predicate) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue)) result.add(issue);
        }
        return result;
    }

    public List<Issue> findOpened() {
        return filterBy(issue -> issue.isOpen());
    }

    public List<Issue> findClosed() {
        return filterBy(issue -> !issue.isOpen());
    }

    public List<Issue> filterByAuthors(String authors) {
        Predicate<String> byAuthors = issue -> issue.equals(authors);
        List<Issue> issues = new ArrayList<>();
        for (Issue issue : repository.findAll())
            if (byAuthors.test(issue.getAuthor())) {
                issues.add(issue);
            }
        return issues;
    }

    public List<Issue> filterByLabels(Collection<? extends String> labels) {
        Predicate<Set<String>> byLabels = issue -> issue.containsAll(labels);
        List<Issue> issues = new ArrayList<>();
        for (Issue issue : repository.findAll())
            if (byLabels.test(issue.getIssueLabels())) {
                issues.add(issue);
            }
        return issues;
    }

    public List<Issue> filterByAssignees(Collection<? extends String> assignees) {
        Predicate<Set<String>> ByAssignees = issue -> issue.containsAll(assignees);
        List<Issue> issues = new ArrayList<>();
        for (Issue issue : repository.findAll())
            if (ByAssignees.test(issue.getIssueAssignees())) {
                issues.add(issue);
            }
        return issues;
    }

    public List<Issue> sortByNewest() {
        Comparator byNewest = Comparator.naturalOrder();
        List<Issue> issues = new ArrayList<>(repository.findAll());
        issues.sort(byNewest);
        return issues;
    }

    public List<Issue> sortByOldest() {
        Comparator byOldest = Comparator.reverseOrder();
        List<Issue> issues = new ArrayList<>(repository.findAll());
        issues.sort(byOldest);
        return issues;
    }

    public void openIssueById(int id) {
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id && !issue.isOpen()) {
                issue.setOpen(true);
            }
        }
    }

    public void closeIssueById(int id) {
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id && issue.isOpen()) {
                issue.setOpen(false);
            }
        }
    }
}
