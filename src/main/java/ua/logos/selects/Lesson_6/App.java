package ua.logos.selects.Lesson_6;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entity.Comment;
import Entity.Post;
import Entity.Tag;
import ua.logos.selects.entity.enums.Status;

public class App {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		//List<Comment> comments = em.createQuery("SELECT c FROM Comment c",Comment.class).getResultList();
		//comments.forEach(c -> System.out.println(c));
		
		Comment commentById = em.createQuery("SELECT c FROM Comment c WHERE c.id = :comment_id",Comment.class)
				.setParameter("comment_id",101).getSingleResult();
System.out.println(commentById);
		// addTags(em);
		// addPost(em);
		//addComment(em);
		em.getTransaction().commit();

		em.close();
		factory.close();

	}

	private static void addTags(EntityManager em) {
		List<String> tags = new ArrayList<>();
		tags.add("Java");
		tags.add("Sql");
		tags.add("ORM");
		tags.add("jPA");
		tags.add("MYSQL");
		tags.add("STS");
		tags.add("eCLIPSE");
		for (int i = 0; i < tags.size(); i++) {
			Tag tag = new Tag();
			tag.setName(tags.get(i));
			em.persist(tag);
		}

	}

	private static void addPost(EntityManager em) {
		for (int i = 1; i < 100; i++) {

			Post post = new Post();
			post.setTitle("Post title" + i);
			post.setContent("Long post content#" + i);
			if (i % 2 == 0)
				post.setStatus(Status.DRAFT);
			if (i % 2 == 1)
				post.setStatus(Status.PUBLISH);
			em.persist(post);
			List<Tag> tags = em.createQuery("Select t from Tag t", Tag.class).getResultList();

		}
	}

	private static void addComment(EntityManager em) {
		for (int i = 1; i < 100; i++) {
			Post post = em.createQuery("Select p From Post p WHERE p.id = :id", Post.class).setParameter("id", i)
					.getSingleResult();
			Comment comment = new Comment();
			comment.setAuthor("Author #" + i);
			comment.setComment("The best comment #" + i);
			comment.setPost(post);
			em.persist(comment);

		}
	}
}
