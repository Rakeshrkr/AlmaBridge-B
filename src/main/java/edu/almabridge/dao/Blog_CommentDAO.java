package edu.almabridge.dao;

import edu.almabridge.model.Blog_Comment;

public interface Blog_CommentDAO {
	public boolean saveComment(Blog_Comment comment);
	public boolean updateComment(Blog_Comment comment);
	public void deleteComment(int commentId);
	public void getComment(String userId);
	public void getComments(int blogId);

}
