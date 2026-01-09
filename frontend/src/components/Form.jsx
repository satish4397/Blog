import { useEffect, useState } from "react";
import { postData, updateData } from "../api/PostApi";

export const Form = ({ data, setData, updateDataApi, setUpdateDataApi }) => {
  const [addData, setAddData] = useState({ title: "", description: "" });

  const isEmpty = Object.keys(updateDataApi).length === 0;

  // prefill form when editing
  useEffect(() => {
    if (!isEmpty) {
      setAddData({
        title: updateDataApi.title || "",
        description: updateDataApi.description || "",
      });
    }
  }, [updateDataApi]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setAddData((prev) => ({ ...prev, [name]: value }));
  };

  const addPostData = async () => {
    try {
      const res = await postData(addData);
      if (res.status === 201) {
        setData([...data, res.data]);
        setAddData({ title: "", description: "" });
      }
    } catch (error) {
      console.error("Error adding post:", error);
    }
  };

  const updatePostData = async () => {
    try {
      const res = await updateData(updateDataApi.id, addData);
      if (res.status === 200) {
        setData((prev) =>
          prev.map((curElem) =>
            curElem.id === res.data.id ? res.data : curElem
          )
        );
        setAddData({ title: "", description: "" });
        setUpdateDataApi({});
      }
    } catch (error) {
      console.error("Error updating post:", error);
    }
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    const action = e.nativeEvent.submitter.value;
    if (action === "Add") {
      addPostData();
    } else if (action === "Edit") {
      updatePostData();
    }
  };

  return (
    <form onSubmit={handleFormSubmit}>
      <div>
        <input
          type="text"
          autoComplete="off"
          id="title"
          name="title"
          placeholder="Add Title"
          value={addData.title}
          onChange={handleInputChange}
        />
      </div>

      <div>
        <input
          type="text"
          autoComplete="off"
          id="description"
          name="description"
          placeholder="Add Description"
          value={addData.description}
          onChange={handleInputChange}
        />
      </div>

      <button type="submit" value={isEmpty ? "Add" : "Edit"}>
        {isEmpty ? "Add" : "Edit"}
      </button>
    </form>
  );
};
