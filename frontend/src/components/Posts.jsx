import { useEffect, useState } from "react";
import { deletePost, getPost } from "../api/PostApi";
import "../App.css";
import { Form } from "./Form";

export const Posts = () => {
  const [data, setData] = useState([]);
  const [updateDataApi, setUpdateDataApi] = useState({});

  const getPostData = async () => {
    try {
      const res = await getPost();
      console.log(res.data); // check shape of response
      setData(res.data);
    } catch (error) {
      console.error("Error fetching posts:", error);
    }
  };

  useEffect(() => {
    getPostData();
  }, []);

 // delete Post
const handleDeletePost = async (id) => {
  try {
    const res = await deletePost(id);
    if (res.status === 200 || res.status === 204) {
      // filter using id since MongoDB returns id
      setData((prev) => prev.filter((curPost) => curPost.id !== id));
      console.log("Post deleted successfully");
    } else {
      console.log("Failed to delete the post:", res.status);
    }
  } catch (error) {
    console.error(error);
  }
};


  // update Post
  const handleUpdatePost = (curElem) => setUpdateDataApi(curElem);

  return (
    <>
      <section className="section-form">
        <Form
          data={data}
          setData={setData}
          updateDataApi={updateDataApi}
          setUpdateDataApi={setUpdateDataApi}
        />
      </section>
      <section className="section-post">
        <ol>
          {data.map((curElem, index) => {
            const { description, title } = curElem;
            return (
              <li key={curElem.id}>
                <p>Title: {title}</p>
                <p>Description: {description}</p>
                <button onClick={() => handleUpdatePost(curElem)}>Edit</button>
                <button
                  className="btn-delete"
                  onClick={() => handleDeletePost(curElem.id)}
                >
                  Delete
                </button>
              </li>
            );
          })}
        </ol>
      </section>
    </>
  );
};
