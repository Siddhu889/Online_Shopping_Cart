const orderInformation = document.querySelector(".order-information");
const orderSummary = document.querySelector(".order-summary");
const deliveryInformation = document.querySelector(".delivery-information");

let cartItems = JSON.parse(localStorage.getItem("cart"));
let customerInfo = JSON.parse(localStorage.getItem("customerInfo"));
let deliveryAddress = JSON.parse(localStorage.getItem("deliveryAddress"));
let orderDetails = JSON.parse(localStorage.getItem("orderDetails"));

if(!cartItems || cartItems.length === 0 || !orderDetails){
   
    orderInformation.innerHTML += `<p>Order information is not available.</p>`;
}

else{
    orderInformation.innerHTML += `
    <div class="order-details">
    <p>
    <strong>Order ID:</strong>
    ${orderDetails.orderId}
    </p>
    
    <p>
    <strong>Order Date:</strong>
    ${orderDetails.orderDate}
    </p>

     <p>
        <strong>Payment Method:</strong>
        ${orderDetails.paymentMethod}
        </p>
        
    </div>`;


let productsHTML = "";

for(let product of cartItems){
    let productTotal = product.price * product.quantity;
    productsHTML +=`<div class="checkout-product">
    <div class="checkout-product-image">
    <img 
      src="${product.image}"
      alt="${product.name}"
      >
      </div>
      <div class="checkout-product-info">
      <h3>${product.name}</h3>
      <p>
      Price:₹${product.price}
      </p>
      <p>
      Quantity:${product.quantity}
      </p>
      <p>
      Product Total:  ₹${productTotal}
      </p>
      </div>
      </div>`;
}

orderSummary.innerHTML +=`
<div class="checkout-products">
${productsHTML}
</div>

<div class="payment-summary">
<p>
Subtotal: ₹${orderDetails.subtotal}
</p>
<p>
Delivery Charge: ₹${orderDetails.deliveryCharge}
</p>
<h2>
Total Amount: ₹${orderDetails.totalAmount}
</h2>
</div>`;

deliveryInformation.innerHTML +=`<div class="delivery-details">

            <p>
                <strong>Customer Name:</strong>
                ${customerInfo.name}
            </p>

            <p>
            <strong>Email:</strong>
                ${customerInfo.email}
            </p>

            <p>
                <strong>Mobile:</strong>
            ${customerInfo.mobile}
            </p>

            <p>
                <strong>Address:</strong>
                ${deliveryAddress.address}
            </p>
            <p>
            <strong>City:</strong>
                ${deliveryAddress.city}
            </p>

            <p>
                <strong>State:</strong>
                ${deliveryAddress.state}
            </p>

            <p>
                <strong>Pincode:</strong>
                ${deliveryAddress.pincode}
            </p>

        </div>`;
}

