import "../App.css";
import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const Create_Project = ({ onAddProject }) => {
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const [showForm, setShowForm] = useState(true); // Initial state is true to show the form
  const [projectName, setProjectName] = useState("");
  const [projectDescription, setProjectDescription] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    const newProject = {
      projectName,
      projectDescription,
      startDate,
      endDate,
    };
    onAddProject(newProject);
    setShowForm(false);
  };

  const handleCancel = () => {
    setShowForm(false); // Set the showForm state to false to hide the form
  };

  if (!showForm) {
    return null; // If showForm is false, return null to not render the form
  }

  return (
    <div>
      <form className="Form" onSubmit={handleSubmit}>
        <h2 className="Project-Header">Create New Project</h2>
        <div>
          <input
            type="text"
            id="projectName"
            name="projectName"
            placeholder="Project Name"
            value={projectName}
            onChange={(e) => setProjectName(e.target.value)}
          />
        </div>
        <div>
          <input
            type="text"
            id="projectDescription"
            name="projectDescription"
            placeholder="Project Description"
            value={projectDescription}
            onChange={(e) => setProjectDescription(e.target.value)}
          />
        </div>
        <div>
          <label className="Date" htmlFor="startDate">
            Start Date
          </label>
          <DatePicker
            id="startDate"
            name="startDate"
            selected={startDate}
            onChange={(date) => setStartDate(date)}
            placeholderText="Select start date"
          />
        </div>
        <div>
          <label className="Date" htmlFor="endDate">
            End Date
          </label>
          <DatePicker
            id="endDate"
            name="endDate"
            selected={endDate}
            onChange={(date) => setEndDate(date)}
            placeholderText="Select end date"
          />
        </div>
        <div>
          <button type="submit" className="submitBtn">
            Submit
          </button>
          <button type="button" className="cancelBtn" onClick={handleCancel}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default Create_Project;
