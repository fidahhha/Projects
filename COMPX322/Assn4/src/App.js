import "./App.css";
import React, { useEffect, useState } from "react";
import { FaTimes } from "react-icons/fa";

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetch("/data.json") // since data.json is located inside public folder
      .then((response) => response.json())
      .then((jsonData) => setData(jsonData))
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  const handleDeleteProject = (projectId) => {
    // Find the index of the project to be deleted
    const projectIndex = data.findIndex(
      (project) => project.projectIdentifier === projectId
    );

    // If the project is found, remove it from the data array
    if (projectIndex !== -1) {
      const updatedData = [...data];
      updatedData.splice(projectIndex, 1);
      setData(updatedData);
    }
  };

  return (
    <div>
      <h1 className="App-Header">Projects</h1>
      <div className="Project-List">
        {data.map((project, index) => (
          <div
            key={project.information}
            className={`Project-Key Project-${index + 1}`}
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
                  {project.start_date} - {project.end_date}
                </span>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
