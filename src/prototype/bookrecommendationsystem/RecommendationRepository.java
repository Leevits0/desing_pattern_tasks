package prototype.bookrecommendationsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendationRepository implements Serializable {
    private final List<Recommendation> recommendations = new ArrayList<>();

    public List<Recommendation> all() {
        return Collections.unmodifiableList(recommendations);
    }

    public void add(Recommendation rec) {
        recommendations.add(rec);
    }

    public Recommendation get(int idx) {
        if (idx < 0 || idx >= recommendations.size()) return null;
        return recommendations.get(idx);
    }

    public int size() { return recommendations.size(); }

    public static void saveToFile(RecommendationRepository repo, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(repo);
        }
    }

    public static RecommendationRepository loadFromFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (!(obj instanceof RecommendationRepository)) {
                throw new IOException("Invalid file format.");
            }
            return (RecommendationRepository) obj;
        }
    }
}
