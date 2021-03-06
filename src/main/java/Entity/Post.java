package Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.logos.selects.entity.enums.Status;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
public class Post extends BaseEntity {

	@Column(name = "title", length = 100)
	private String title;
	@Column(name = "content", length = 1000)
	private String content;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<Comment>();

	@ManyToMany
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags = new ArrayList<Tag>();

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + ", status=" + status + ", getId()=" + getId() + "]";
	}

}
