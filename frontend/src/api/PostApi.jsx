import axios from "axios";

// Use environment variable or fallback
const api = axios.create({
  baseURL: process.env.REACT_APP_API_URL || "https://blog-1-rs3h.onrender.com/api",
});

// GET all posts
export const getPost = () => api.get("/posts");

// DELETE post by id
export const deletePost = (id) => api.delete(`/posts/${id}`);

// POST new post
export const postData = (post) =>
  api.post("/posts", post, {
    headers: { "Content-Type": "application/json" },
  });

// PUT update post
export const updateData = (id, post) =>
  api.put(`/posts/${id}`, post, {
    headers: { "Content-Type": "application/json" },
  });
