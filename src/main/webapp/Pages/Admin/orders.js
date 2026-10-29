
const ordersTableBody = document.getElementById("orders-table-body");
const searchInput = document.getElementById("search-order");
const searchButton = document.getElementById("search-button");
const statusFilter = document.getElementById("order-status-filter");

const contextPath = document.body.dataset.contextPath;
console.log("Context path:", contextPath);

function applyFilters() {
    const searchText = searchInput.value.trim().toLowerCase();
    const selectedStatus = statusFilter.value.toLowerCase();

    const rows = ordersTableBody.querySelectorAll("tr");

    rows.forEach(function (row) {
        const cells = row.cells;

        // Skip the "No orders found" row.
        if (cells.length !== 7) {
            return;
        }

        const orderId = cells[0].textContent.toLowerCase();
        const customerName = cells[1].textContent.toLowerCase();
        const orderStatus = cells[5].textContent.trim().toLowerCase();

        const matchesSearch =
            orderId.includes(searchText) ||
            customerName.includes(searchText);

        const matchesStatus =
            selectedStatus === "all" ||
            orderStatus === selectedStatus;

        row.style.display =
            matchesSearch && matchesStatus ? "" : "none";
    });
}


async function updateOrderStatus(orderId) {

    const newStatus = prompt(
        "Enter new status: pending, confirmed, shipped, delivered or cancelled"
    );

    if (newStatus === null || newStatus.trim() === "") {
        return;
    }

    const allowedStatuses = [
        "pending",
        "confirmed",
        "shipped",
        "delivered",
        "cancelled"
    ];

    const status = newStatus.trim().toLowerCase();

    if (!allowedStatuses.includes(status)) {
        alert("Invalid status. Please enter pending, confirmed, shipped, delivered or cancelled.");
        return;
    }

    try {
        const formData = new URLSearchParams();
        formData.append("action", "updateStatus");
        formData.append("orderId", orderId);
        formData.append("status", status);

        const response = await fetch(
            contextPath + "/order",
            {
                method: "POST",
                headers: {
                    "Content-Type":
                        "application/x-www-form-urlencoded;charset=UTF-8"
                },
                body: formData.toString()
            }
        );

        const message = await response.text();

        if (!response.ok) {
            alert(
                "Update failed. HTTP Status: " +
                response.status +
                "\n" + message.substring(0, 250)
            );
            return;
        }

        alert(message);

        // Reload the latest orders from the database.
		window.location.assign("/Online_Shopping_Cart/order");

    } catch (error) {
        console.error("Order update error:", error);

        alert(
            "Could not contact the order servlet. " +
            "Please check that Tomcat is running."
        );
    }
}


searchButton.addEventListener("click", applyFilters);
searchInput.addEventListener("input", applyFilters);
statusFilter.addEventListener("change", applyFilters);

