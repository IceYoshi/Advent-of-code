package day19;

import common.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author permi
 */
public class Day19 extends Day {

    private record Substitution(String from, String to) {

    }

    private List<Substitution> substitutions = new ArrayList<>();

    private String initialMolecule;

    private Set<String> uniqueMolecules = new HashSet<>();

    public Day19() {
        super(FileType.Input);

        for (String line : input.lines().toList()) {
            if (line.isBlank()) {
                continue;
            }
            if (line.contains("=>")) {
                String tokens[] = line.replaceAll(" ", "").split("=>");
                substitutions.add(new Substitution(tokens[0], tokens[1]));
            } else {
                initialMolecule = line;
            }
        }

        for(Substitution sub: substitutions) {
            traverse(sub);
        }
        IO.println("Number of unique molecules: " + uniqueMolecules.size());
    }

    private void traverse(Substitution sub) {

        for (int i = 0; i <= initialMolecule.length() - sub.from.length(); i++) {
            if (initialMolecule.substring(i, i + sub.from.length()).equals(sub.from)) {
                uniqueMolecules.add(
                        initialMolecule.substring(0, i)
                        + sub.to
                        + initialMolecule.substring(i + sub.from.length(), initialMolecule.length())
                );
            }
        }
    }

}
