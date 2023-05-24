import React, { useEffect, useState } from "react";
import Create_Project from "./components/Create-Project";
import { format } from "date-fns";
import { FaTimes } from "react-icons/fa";
import "./App.css";

function App() {
  const [data, setData] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [sortOption, setSortOption] = useState("start_date");
  const [sortOrder, setSortOrder] = useState("asc");

  useEffect(() => {
    fetch("/data.json")
      .then((response) => response.json())
      .then((jsonData) => setData(jsonData))
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  const handleDeleteProject = (projectId) => {
    const updatedData = data.filter(
      (project) => project.projectIdentifier !== projectId
    );
    setData(updatedData);
  };

  const toggleForm = () => {
    setShowForm(!showForm);
  };

  const handleAddProject = (newProject) => {
    const startDate = format(newProject.startDate, "yyyy-MM-dd");
    const endDate = format(newProject.endDate, "yyyy-MM-dd");
    const projectNumber = data.length + 1;

    const colors = ["#fdb080", "#f78e82", "#d885a2", "#df9185", "#b2acd6"];

    const randomColor = colors[Math.floor(Math.random() * colors.length)];

    const updatedProject = {
      projectIdentifier: `${projectNumber}`,
      projectName: newProject.projectName,
      start_date: startDate,
      end_date: endDate,
      color: randomColor,
    };
    setData([...data, updatedProject]);
    setShowForm(false);
  };

  const handleSortOptionChange = (e) => {
    setSortOption(e.target.value);
  };

  const handleSortOrderChange = (e) => {
    setSortOrder(e.target.value);
  };

  const handleSort = () => {
    const sortedData = [...data];
    sortedData.sort((a, b) => {
      const valueA = sortOption === "start_date" ? a.start_date : a.projectName;
      const valueB = sortOption === "start_date" ? b.start_date : b.projectName;
      const comparison = valueA.localeCompare(valueB);

      return sortOrder === "asc" ? comparison : -comparison;
    });

    setData(sortedData);
  };

  return (
    <div>
      <h1 className="App-Header">Projects</h1>
      <div className="sort-section">
        <label className="sort-option">Sort by:</label>
        <select
          id="sort-option"
          value={sortOption}
          onChange={handleSortOptionChange}
        >
          <option value="start_date">Start Date</option>
          <option value="projectName">Project Name</option>
        </select>
        <label className="sort-option">Order:</label>
        <select
          id="sort-order"
          value={sortOrder}
          onChange={handleSortOrderChange}
        >
          <option value="asc">Ascending</option>
          <option value="desc">Descending</option>
        </select>
        <button className="sortBtn" onClick={handleSort}>
          Sort
        </button>
      </div>

      <div className="Project-List">
        {data.map((project, index) => (
          <div
            key={project.information}
            className={`Project Project-${index + 1}`}
            style={{ backgroundColor: project.color }}
          >
            <div className="Project-Details">
              <div className="Project-Column">
                <button
                  className="Delete-Button"
                  onClick={() => handleDeleteProject(project.projectIdentifier)}
                >
                  <FaTimes />
                </button>
              </div>
              <div className="Project-Column">
                <span className="Project-ID">
                  Project {project.projectIdentifier}
                </span>
              </div>
              <div className="Project-Column">
                <span className="Project-Value">{project.projectName}</span>
              </div>
              <div className="Project-Column">
                <span className="Project-Value">
                  {project.start_date.toString()} -{" "}
                  {project.end_date.toString()}
                </span>
              </div>
            </div>
          </div>
        ))}
      </div>
      <button className="Create_Project" onClick={toggleForm}>
        Create Project
      </button>
      {showForm && (
        <Create_Project
          onAddProject={handleAddProject}
          existingColors={data.map((project) => project.color)}
        />
      )}
      <div></div>
    </div>
  );
}

export default App;
