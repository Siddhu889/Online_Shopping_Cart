
const categoryForm = document.getElementById("category-form");

const categoryId = document.getElementById("category-id");
const categoryName = document.getElementById("category-name");
const categoryDescription = document.getElementById("category-description");

const formTitle = document.getElementById("form-title");
const saveButton = document.getElementById("save-button");

const searchCategoryId = document.getElementById("search-category-id");
const searchMessage = document.getElementById("search-message");

const categoryTableBody = document.getElementById("category-table-body");
const tableMessage = document.getElementById("table-message");

const cancelButton = document.getElementById("cancel-button");
const searchButton = document.getElementById("search-button");
const showAllButton = document.getElementById("show-all-button");

// Get the application path from the script element in JSP
const scriptElement = document.currentScript;

const contextPath = scriptElement
    ? scriptElement.dataset.contextPath
    : "";

const categoryUrl = contextPath + "/category";

// Display categories in the table
function displayCategories(categoryList) {

    categoryTableBody.innerHTML = "";

    if (categoryList.length === 0) {
        tableMessage.textContent = "No categories found.";
        return;
    }

    tableMessage.textContent = "";

    categoryList.forEach(function(category) {

        const row = document.createElement("tr");

        const idCell = document.createElement("td");
        const nameCell = document.createElement("td");
        const descriptionCell = document.createElement("td");
        const actionCell = document.createElement("td");

        idCell.textContent = category.id;
        nameCell.textContent = category.name;
        descriptionCell.textContent = category.description;

        [idCell, nameCell, descriptionCell, actionCell]
            .forEach(function(cell) {
                cell.style.padding = "12px";
                cell.style.borderBottom = "1px solid #ddd";
            });

        // Edit button
        const editButton = document.createElement("button");

        editButton.textContent = "Edit";
        editButton.type = "button";

        editButton.addEventListener("click", function() {
            editCategory(category);
        });

        // Delete button
        const deleteButton = document.createElement("button");

        deleteButton.textContent = "Delete";
        deleteButton.type = "button";
        deleteButton.style.marginLeft = "8px";

        deleteButton.addEventListener("click", function() {
            deleteCategory(category.id);
        });

        actionCell.append(editButton, deleteButton);

        row.append(
            idCell,
            nameCell,
            descriptionCell,
            actionCell
        );

        categoryTableBody.appendChild(row);
    });
}

// View all categories from MySQL
async function loadCategories() {

    tableMessage.textContent = "Loading categories...";

    try {

        const response = await fetch(
            categoryUrl + "?action=all"
        );

        if (!response.ok) {
            throw new Error("Unable to load categories.");
        }

        const categories = await response.json();

        displayCategories(categories);

    } catch (error) {

        categoryTableBody.innerHTML = "";
        tableMessage.textContent = error.message;

    }
}

// Add or update category
categoryForm.addEventListener("submit", async function(event) {

    event.preventDefault();

    const name = categoryName.value.trim();
    const description = categoryDescription.value.trim();

    if (name === "" || description === "") {
        alert("Please enter category name and description.");
        return;
    }

    if (name.length > 100) {
        alert("Category name cannot exceed 100 characters.");
        return;
    }

    if (description.length > 500) {
        alert("Category description cannot exceed 500 characters.");
        return;
    }

    const action = categoryId.value === ""
        ? "add"
        : "update";

    const formData = new URLSearchParams();

    formData.append("action", action);
    formData.append("categoryName", name);
    formData.append("categoryDescription", description);

    if (action === "update") {
        formData.append("id", categoryId.value);
    }

    saveButton.disabled = true;

    try {

        const response = await fetch(categoryUrl, {
            method: "POST",
            headers: {
                "Content-Type":
                    "application/x-www-form-urlencoded;charset=UTF-8"
            },
            body: formData.toString()
        });

        const result = await response.json();

        if (!response.ok || !result.success) {
            alert(result.message || "Category operation failed.");
            return;
        }

        alert(result.message);

        clearForm();
        searchMessage.textContent = "";

        await loadCategories();

    } catch (error) {

        alert("Unable to connect to the server. Please try again.");

        console.error(error);

    } finally {

        saveButton.disabled = false;

    }
});

// Load selected category into the form for editing
function editCategory(category) {

    categoryId.value = category.id;
    categoryName.value = category.name;
    categoryDescription.value = category.description;

    formTitle.textContent = "Update Category";
    saveButton.textContent = "Update Category";

    window.scrollTo(0, 0);
}

// Delete category from MySQL
async function deleteCategory(id) {

    const confirmDelete = confirm(
        "Are you sure you want to delete this category?"
    );

    if (!confirmDelete) {
        return;
    }

    const formData = new URLSearchParams();

    formData.append("action", "delete");
    formData.append("id", id);

    try {

        const response = await fetch(categoryUrl, {
            method: "POST",
            headers: {
                "Content-Type":
                    "application/x-www-form-urlencoded;charset=UTF-8"
            },
            body: formData.toString()
        });

        const result = await response.json();

        if (!response.ok || !result.success) {
            alert(result.message || "Unable to delete category.");
            return;
        }

        alert(result.message);

        searchMessage.textContent = "";

        await loadCategories();

    } catch (error) {

        alert("Unable to connect to the server. Please try again.");

        console.error(error);

    }
}

// Search category by ID
searchButton.addEventListener("click", async function() {

    const idValue = searchCategoryId.value.trim();
    const id = Number(idValue);

    if (idValue === "" || !Number.isInteger(id) || id <= 0) {

        searchMessage.textContent =
            "Please enter a valid category ID.";

        displayCategories([]);
        return;
    }

    try {

        const response = await fetch(
            categoryUrl + "?action=search&id=" +
            encodeURIComponent(id)
        );

        const result = await response.json();

        if (!response.ok) {

            searchMessage.textContent =
                result.message || "Category not found.";

            displayCategories([]);
            return;
        }

        searchMessage.textContent = "Category found.";

        displayCategories([result]);

    } catch (error) {

        searchMessage.textContent =
            "Unable to connect to the server.";

        displayCategories([]);

        console.error(error);

    }
});

// View all categories
showAllButton.addEventListener("click", async function() {

    searchCategoryId.value = "";
    searchMessage.textContent = "";

    await loadCategories();
});

// Clear form
function clearForm() {

    categoryForm.reset();

    categoryId.value = "";

    formTitle.textContent = "Add New Category";
    saveButton.textContent = "Add Category";
}

cancelButton.addEventListener("click", function() {
    clearForm();
});

// Load categories when the page opens
loadCategories();
