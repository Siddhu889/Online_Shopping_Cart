/*const products = [

    // Electronics products: 101 - 116
    {
        id: "101",
        name: "Wireless HeadPhones",
        price: 2000,
        image: "../../Images/electronics/headphone (1).jpg",
        description: "Enjoy high-quality sound with comfortable wireless headphones. Perfect for music, movies and online meetings.",
        details: "These wireless headphones provide clear and powerful sound with balanced bass and good audio quality. They offer up to 30 hours of battery backup and can be fully charged in approximately 2 hours. The headphones support Bluetooth connectivity and are designed for comfortable long-term use.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: boAt", "High-quality audio output", "30 hours battery backup", "2 hours charging time", "1 year warranty", "Bluetooth wireless connectivity", "Lightweight and durable design"]
    },

    {
        id: "102",
        name: "Wireless Headphones",
        price: 5000,
        image: "../../Images/electronics/headphone (2).jpg",
        description: "Enjoy high-quality sound with comfortable wireless headphones. Perfect for music, movies and online meetings.",
        details: "These wireless headphones provide clear and powerful sound with balanced bass and good audio quality. They offer up to 30 hours of battery backup and can be fully charged in approximately 2 hours. The headphones support Bluetooth connectivity and are designed for comfortable long-term use.",
        category: "Electronics",
        availability: "Out of Stock",
        features: ["Brand: boAt", "High-quality audio output", "30 hours battery backup", "2 hours charging time", "1 year warranty", "Bluetooth wireless connectivity", "Lightweight and durable design"]
    },

    {
        id: "103",
        name: "Wireless HeadPhones",
        price: 6000,
        image: "../../Images/electronics/headphone (3).jpg",
        description: "Enjoy high-quality sound with comfortable wireless headphones. Perfect for music, movies and online meetings.",
        details: "These wireless headphones provide clear and powerful sound with balanced bass and good audio quality. They offer up to 30 hours of battery backup and can be fully charged in approximately 2 hours. The headphones support Bluetooth connectivity and are designed for comfortable long-term use.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Sony", "High-quality audio output", "30 hours battery backup", "2 hours charging time", "1 year warranty", "Bluetooth wireless connectivity", "Lightweight and durable design"]
    },

    {
        id: "104",
        name: "Wireless Headphones",
        price: 10000,
        image: "../../Images/electronics/headphone (4).jpg",
        description: "Enjoy high-quality sound with comfortable wireless headphones. Perfect for music, movies and online meetings.",
        details: "Premium wireless headphones with powerful sound and enhanced bass performance. They offer up to 45 hours of battery backup and support fast charging. A short charging session can provide several hours of listening, making them suitable for long use.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Sennheiser", "Premium audio quality", "45 hours battery backup", "Fast charging support", "Bluetooth 5.2 connectivity", "2 year warranty", "Premium comfortable ear cushions"]
    },

    {
        id: "105",
        name: "Laptop",
        price: 65000,
        image: "../../Images/electronics/laptop (1).jpg",
        description: "Powerful and reliable laptop with a modern design. Perfect for work, study, entertainment, and everyday use.",
        details: "This laptop is designed for everyday work and study. It provides smooth performance for browsing, documents, online classes, video streaming, and other regular tasks. Its battery provides several hours of usage and the laptop supports fast charging.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: HP", "Intel Core i5 processor", "8GB RAM", "512GB SSD storage", "15.6-inch Full HD display", "1 year warranty", "Fast charging support"]
    },

    {
        id: "106",
        name: "Laptop",
        price: 78000,
        image: "../../Images/electronics/laptop (2).jpg",
        description: "Powerful and reliable laptop with a modern design. Perfect for work, study, entertainment, and everyday use.",
        details: "This laptop offers powerful performance for programming, professional work, multitasking, and entertainment. The SSD provides fast application loading and the battery is designed to provide long working hours with efficient power management.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Dell", "Intel Core i7 processor", "16GB RAM", "512GB SSD storage", "15.6-inch Full HD display", "1 year warranty", "Backlit keyboard"]
    },

    {
        id: "107",
        name: "Laptop",
        price: 65000,
        image: "../../Images/electronics/laptop (3).jpg",
        description: "Lightweight laptop with a fast processor and clear display. Suitable for students, office work, and daily tasks.",
        details: "This laptop provides smooth and reliable performance for students and office users. It handles web browsing, document editing, online meetings, and daily applications efficiently. Its SSD provides quick startup and faster file access.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Lenovo", "Intel Core i5 processor", "8GB RAM", "512GB SSD storage", "14-inch Full HD display", "1 year warranty", "Lightweight design"]
    },

    {
        id: "108",
        name: "Laptop",
        price: 92000,
        image: "../../Images/electronics/laptop (4).jpg",
        description: "High-performance laptop with excellent speed and storage. Great for professional work, programming, and multitasking.",
        details: "This high-performance laptop is suitable for professional work, programming, multitasking, and demanding applications. It offers fast processing, high-speed storage, and an efficient cooling system for longer working sessions.",
        category: "Electronics",
        availability: "Out of Stock",
        features: ["Brand: ASUS", "Intel Core i7 processor", "16GB RAM", "1TB SSD storage", "15.6-inch Full HD display", "2 year warranty", "Enhanced cooling system"]
    },

    {
        id: "109",
        name: "Laptop",
        price: 55000,
        image: "../../Images/electronics/laptop (5).jpg",
        description: "Affordable laptop with a compact design and dependable performance. Ideal for online classes, browsing, and office applications.",
        details: "This laptop is designed for students and everyday users. It provides reliable performance for online classes, browsing, office applications, video calls, and entertainment. The SSD helps the system start quickly and load applications smoothly.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Acer", "Intel Core i3 processor", "8GB RAM", "512GB SSD storage", "15.6-inch Full HD display", "1 year warranty", "Long-lasting battery"]
    },

    {
        id: "110",
        name: "Laptop",
        price: 87000,
        image: "../../Images/electronics/laptop (6).jpg",
        description: "Premium laptop with a stylish design, powerful performance, and a bright display for work, entertainment, and creativity.",
        details: "This premium laptop provides powerful performance for professional work, programming, entertainment, and creative tasks. It offers fast processing, high-speed storage, and a bright display for a smooth overall user experience.",
        category: "Electronics",
        availability: "Out of Stock",
        features: ["Brand: Apple", "Powerful high-performance processor", "16GB RAM", "512GB SSD storage", "13.6-inch high-resolution display", "1 year warranty", "Long battery life"]
    },

    {
        id: "111",
        name: "Phone",
        price: 47000,
        image: "../../Images/electronics/phone (1).jpg",
        description: "Modern and classy Phones for Genz peoples",
        details: "This modern smartphone offers smooth performance with a bright display and powerful processing. It is suitable for gaming, social media, video streaming, photography, and everyday use. The efficient battery provides long usage throughout the day.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Samsung", "AMOLED high-resolution display", "8GB RAM", "256GB internal storage", "50MP high-quality camera", "Fast charging support", "1 year warranty"]
    },

    {
        id: "112",
        name: "Phone",
        price: 35000,
        image: "../../Images/electronics/phone (2).jpg",
        description: "Powerful smartphone with a clear display, smooth performance, and long-lasting battery.",
        details: "This smartphone combines smooth performance with a sharp display and dependable battery life. It is designed for browsing, photography, online classes, video calls, gaming, and daily applications. The fast processor provides a responsive experience.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: OnePlus", "120Hz smooth display", "8GB RAM", "128GB internal storage", "50MP main camera", "5000mAh battery", "Fast charging support", "1 year warranty"]
    },

    {
        id: "113",
        name: "Phone",
        price: 28000,
        image: "../../Images/electronics/phone (3).jpg",
        description: "Affordable smartphone with a modern look, excellent camera, and reliable performance.",
        details: "This affordable smartphone provides reliable performance for everyday activities. It has a clear display, capable camera, efficient battery, and enough storage for applications, photos, and videos. It is suitable for students and regular users.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Redmi", "Full HD+ display", "6GB RAM", "128GB internal storage", "50MP main camera", "5000mAh battery", "Dual SIM support", "1 year warranty"]
    },

    {
        id: "114",
        name: "Phone",
        price: 52000,
        image: "../../Images/electronics/phone (4).jpg",
        description: "Premium phone with a bright display, powerful processor, and advanced camera features.",
        details: "This premium smartphone is built for users who want powerful performance and advanced photography. It provides a bright display, fast processing, strong battery performance, and smooth multitasking for gaming, entertainment, and professional use.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Apple", "High-resolution OLED display", "8GB RAM", "256GB internal storage", "Advanced dual camera system", "Face recognition support", "Fast charging support", "1 year warranty"]
    },

    {
        id: "115",
        name: "Phone",
        price: 41000,
        image: "../../Images/electronics/phone (5).jpg",
        description: "Sleek and lightweight smartphone with fast performance, great camera quality, and ample storage.",
        details: "This sleek smartphone offers fast and reliable performance in a lightweight body. It provides excellent camera quality, smooth application performance, and enough storage for photos, videos, games, and daily applications. Its battery is designed for all-day usage.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Vivo", "AMOLED display", "8GB RAM", "256GB internal storage", "50MP camera with AI features", "5000mAh battery", "In-display fingerprint sensor", "1 year warranty"]
    },

    {
        id: "116",
        name: "Phone",
        price: 60000,
        image: "../../Images/electronics/phone (6).jpg",
        description: "High-end smartphone with a stylish design, excellent camera, fast processor, and smooth user experience.",
        details: "This high-end smartphone delivers powerful performance for demanding applications, gaming, photography, and entertainment. It features a high-quality display, advanced camera system, fast processing, and long battery life for a premium smartphone experience.",
        category: "Electronics",
        availability: "In Stock",
        features: ["Brand: Google Pixel", "High-resolution OLED display", "12GB RAM", "256GB internal storage", "Advanced AI-powered camera", "Fast charging support", "Wireless charging support", "1 year warranty"]
    },

    // Fashion products: 201 - 224

    {
        id: "201",
        name: "Stylish Lipstick",
        price: 2000,
        image: "../../Images/fashion/beauty (1).jpg",
        description: "A stylish and long-lasting lipstick that adds a beautiful pop of color to your lips. Perfect for everyday makeup and special occasions.",
        details: "This stylish lipstick provides a smooth and comfortable application with vibrant color and a beautiful finish. Its moisturizing formula helps keep the lips soft while providing long-lasting color throughout the day. The lightweight texture makes it comfortable for regular use and suitable for different occasions.",
        category: "Beauty",
        availability: "In Stock",
        features: ["Long-lasting color", "Vibrant finish", "Moisturizing formula", "Smooth application", "Lightweight texture", "Comfortable for daily use", "Easy to carry"]
    },

    {
        id: "202",
        name: "Sunscreen Lotion",
        price: 1500,
        image: "../../Images/fashion/beauty (2).jpg",
        description: "A lightweight sunscreen lotion that helps protect your skin from harmful UV rays. Perfect for everyday outdoor activities and regular skincare.",
        details: "This sunscreen lotion is designed to provide effective protection against harmful UV rays while keeping the skin comfortable. Its lightweight and non-greasy formula absorbs easily into the skin without leaving a heavy feeling. It is suitable for everyday use and can easily become part of your daily skincare routine.",
        category: "Beauty",
        availability: "In Stock",
        features: ["Broad-spectrum protection", "Lightweight texture", "Non-greasy formula", "Quick absorption", "Daily skincare use", "Easy application", "Comfortable on skin"]
    },

    {
        id: "203",
        name: "Beauty Makeup Kit",
        price: 2500,
        image: "../../Images/fashion/beauty (3).jpg",
        description: "A complete beauty makeup kit containing essential products for creating an attractive and stylish makeup look. Perfect for everyday use and special occasions.",
        details: "This beauty makeup kit provides essential makeup products in a convenient collection. The products are easy to apply and can be used to create both simple everyday looks and attractive party looks. Its compact design also makes it convenient to carry while travelling.",
        category: "Beauty",
        availability: "In Stock",
        features: ["Complete makeup essentials", "Easy to apply", "Smooth finish", "Compact design", "Travel friendly", "Suitable for beginners", "Suitable for everyday use"]
    },

    {
        id: "204",
        name: "Premium Face Cream",
        price: 1800,
        image: "../../Images/fashion/beauty (4).jpg",
        description: "A premium face cream designed to provide a soft and smooth feeling to the skin. Perfect for including in your daily skincare routine.",
        details: "This premium face cream has a lightweight texture that spreads easily across the skin. It is designed for regular skincare use and provides a smooth and comfortable feeling after application. The cream is easy to apply and can be used as part of a simple daily beauty routine.",
        category: "Beauty",
        availability: "Out of Stock",
        features: ["Lightweight formula", "Smooth texture", "Easy absorption", "Daily skincare use", "Non-sticky feel", "Easy application", "Comfortable texture"]
    },

    {
        id: "205",
        name: "Makeup Brush Set",
        price: 1200,
        image: "../../Images/fashion/beauty (5).jpg",
        description: "A useful makeup brush set designed for smooth and precise makeup application. Perfect for beginners and regular makeup users.",
        details: "This makeup brush set contains useful brushes designed for applying different types of makeup products. The soft bristles provide comfortable application while the ergonomic handles offer a good grip. The brushes are easy to clean and convenient to use for everyday makeup.",
        category: "Beauty",
        availability: "In Stock",
        features: ["Soft bristles", "Precise application", "Comfortable grip", "Multiple brushes", "Easy to clean", "Beginner friendly", "Suitable for daily makeup"]
    },

    {
        id: "206",
        name: "Luxury Beauty Product",
        price: 3000,
        image: "../../Images/fashion/beauty (6).jpg",
        description: "A premium beauty product designed to add an elegant touch to your personal beauty collection. Perfect for personal use and gifting.",
        details: "This luxury beauty product combines an attractive appearance with convenient everyday use. Its premium presentation makes it a great addition to a personal beauty collection and also makes it suitable for gifting. The product is designed for customers who prefer stylish and premium beauty products.",
        category: "Beauty",
        availability: "Out of Stock",
        features: ["Premium quality", "Elegant design", "Attractive packaging", "Easy to use", "Long-lasting performance", "Suitable for gifting", "Stylish appearance"]
    },
    {
        id: "207",
        name: "Classic Men's Shirt",
        price: 1800,
        image: "../../Images/fashion/men (1).jpg",
        description: "A classic men's shirt designed with a comfortable fit and stylish appearance. Perfect for office wear, casual outings and everyday use.",
        details: "This classic men's shirt is designed to provide a comfortable fit while maintaining a smart and stylish appearance. Its versatile design allows it to be paired with trousers, jeans or other casual and formal clothing. It is suitable for office, casual outings and everyday wear.",
        category: "Men",
        availability: "In Stock",
        features: ["Comfortable fabric", "Classic design", "Regular fit", "Stylish appearance", "Easy to maintain", "Suitable for office wear", "Suitable for daily wear"]
    },

    {
        id: "208",
        name: "Men's Casual Outfit",
        price: 2500,
        image: "../../Images/fashion/men (2).jpg",
        description: "A fashionable men's casual outfit designed for a relaxed and modern appearance. Perfect for outings, travel and everyday activities.",
        details: "This casual men's outfit combines comfort and modern fashion. The comfortable material allows easy movement while the stylish design creates a smart casual appearance. It can be worn for shopping, travel, casual gatherings and everyday activities.",
        category: "Men",
        availability: "In Stock",
        features: ["Comfortable material", "Modern design", "Casual style", "Easy to wear", "Good fitting", "Suitable for outings", "Suitable for travel"]
    },

    {
        id: "209",
        name: "Men's Premium Jacket",
        price: 4500,
        image: "../../Images/fashion/men (3).jpg",
        description: "A premium men's jacket designed to provide a stylish and confident appearance. Perfect for casual outings, travel and outdoor activities.",
        details: "This premium men's jacket features a stylish design combined with comfortable construction. It can be worn over different outfits and is suitable for travel, casual gatherings and outdoor activities. The durable construction is designed for regular use while maintaining its fashionable appearance.",
        category: "Men",
        availability: "Out of Stock",
        features: ["Premium material", "Stylish design", "Comfortable fit", "Durable construction", "Modern appearance", "Suitable for outdoor use", "Versatile styling"]
    },

    {
        id: "210",
        name: "Men's Formal Wear",
        price: 3500,
        image: "../../Images/fashion/men (4).jpg",
        description: "Elegant men's formal wear designed for professional and special occasions. Perfect for office meetings, business events and formal gatherings.",
        details: "This elegant formal wear is designed to create a neat and sophisticated appearance. The comfortable fitting allows easy movement while the premium-looking fabric provides a professional appearance. It is suitable for office meetings, business events, interviews and formal occasions.",
        category: "Men",
        availability: "In Stock",
        features: ["Elegant design", "Comfortable fit", "Professional appearance", "Premium fabric", "Neat finishing", "Suitable for office wear", "Suitable for formal occasions"]
    },

    {
        id: "211",
        name: "Men's Casual T-Shirt",
        price: 1000,
        image: "../../Images/fashion/men (5).jpg",
        description: "A comfortable men's casual T-shirt designed for everyday wear. Perfect for shopping, travel, casual outings and relaxing activities.",
        details: "This casual T-shirt is made for everyday comfort and easy styling. Its simple design makes it easy to pair with jeans, trousers or shorts. The comfortable material provides a relaxed feeling and makes the T-shirt suitable for regular daily activities.",
        category: "Men",
        availability: "In Stock",
        features: ["Soft fabric", "Comfortable fit", "Casual design", "Breathable material", "Easy to wash", "Lightweight", "Suitable for everyday wear"]
    },

    {
        id: "212",
        name: "Men's Fashion Outfit",
        price: 3200,
        image: "../../Images/fashion/men (6).jpg",
        description: "A stylish men's fashion outfit designed for a modern and attractive appearance. Perfect for casual gatherings, outings and social occasions.",
        details: "This fashionable men's outfit is designed for customers who want a modern and confident appearance. The comfortable material provides easy movement while the stylish design makes it suitable for different social occasions. It can be combined with different footwear and accessories.",
        category: "Men",
        availability: "Out of Stock",
        features: ["Modern fashion style", "Comfortable material", "Stylish appearance", "Good fitting", "Easy to style", "Suitable for outings", "Suitable for social occasions"]
    },

    {
        id: "213",
        name: "Classic Casual Shoes",
        price: 2200,
        image: "../../Images/fashion/shoes (1).jpg",
        description: "Comfortable casual shoes designed for everyday activities and regular outings. Perfect for walking, shopping, travel and casual occasions.",
        details: "These classic casual shoes are designed to provide comfort during everyday activities. The lightweight construction and comfortable sole make them suitable for regular walking and outings. Their simple design can be easily paired with different casual outfits.",
        category: "Shoes",
        availability: "In Stock",
        features: ["Comfortable sole", "Casual design", "Lightweight construction", "Good grip", "Durable material", "Easy to wear", "Suitable for daily use"]
    },

    {
        id: "214",
        name: "Stylish Fashion Shoes",
        price: 2800,
        image: "../../Images/fashion/shoes (2).jpg",
        description: "Stylish fashion shoes designed to complement modern outfits. Perfect for casual outings, gatherings and fashionable occasions.",
        details: "These stylish fashion shoes are designed to combine an attractive appearance with everyday comfort. The modern design makes them easy to pair with different outfits, while the comfortable construction allows them to be worn during casual occasions and outings.",
        category: "Shoes",
        availability: "In Stock",
        features: ["Modern design", "Comfortable fit", "Durable sole", "Good grip", "Lightweight feel", "Stylish appearance", "Suitable for casual occasions"]
    },

    {
        id: "215",
        name: "Premium Sports Shoes",
        price: 3500,
        image: "../../Images/fashion/shoes (3).jpg",
        description: "Premium sports shoes designed for comfortable movement and active lifestyles. Perfect for walking, exercise and everyday activities.",
        details: "These premium sports shoes are designed with a lightweight construction and supportive sole for comfortable movement. The breathable material helps provide a comfortable experience during regular activities. They are suitable for walking, light exercise and everyday use.",
        category: "Shoes",
        availability: "Out of Stock",
        features: ["Lightweight design", "Supportive sole", "Comfortable cushioning", "Good grip", "Breathable material", "Flexible construction", "Suitable for walking"]
    },

    {
        id: "216",
        name: "Elegant Women's Shoes",
        price: 2600,
        image: "../../Images/fashion/shoes (4).jpg",
        description: "Elegant women's shoes designed to add a fashionable touch to your outfit. Perfect for parties, events, casual outings and special occasions.",
        details: "These elegant women's shoes feature a stylish appearance that can complement different types of outfits. The comfortable construction makes them suitable for regular use, while the attractive design makes them a good choice for parties, gatherings and special occasions.",
        category: "Shoes",
        availability: "In Stock",
        features: ["Elegant appearance", "Stylish design", "Comfortable fit", "Durable construction", "Attractive finish", "Suitable for events", "Easy to pair with outfits"]
    },

    {
        id: "217",
        name: "Comfort Walking Shoes",
        price: 2400,
        image: "../../Images/fashion/shoes (5).jpg",
        description: "Comfortable walking shoes designed for people who spend a lot of time on their feet. Perfect for walking, travel and everyday activities.",
        details: "These walking shoes are designed to provide comfort and support during regular walking. The cushioned sole helps provide a comfortable feel while the lightweight construction makes them easy to wear for longer periods. They are suitable for walking, travel and daily activities.",
        category: "Shoes",
        availability: "In Stock",
        features: ["Cushioned sole", "Comfortable fitting", "Lightweight construction", "Good support", "Breathable material", "Durable design", "Suitable for long walks"]
    },

    {
        id: "218",
        name: "Premium Fashion Footwear",
        price: 4000,
        image: "../../Images/fashion/shoes (6).jpg",
        description: "Premium fashion footwear designed for customers who want a combination of style, comfort and quality. Perfect for fashionable occasions and special events.",
        details: "This premium footwear combines a fashionable appearance with comfortable construction. The attractive design makes it suitable for special occasions and social gatherings, while the durable sole provides reliable support for regular use. It can be paired with different formal and fashionable outfits.",
        category: "Shoes",
        availability: "Out of Stock",
        features: ["Premium quality", "Stylish appearance", "Comfortable interior", "Durable sole", "Modern design", "Good support", "Suitable for special occasions"]
    },

    {
        id: "219",
        name: "Women's Stylish Dress",
        price: 2800,
        image: "../../Images/fashion/women (1).jpg",
        description: "A stylish women's dress designed to provide an elegant and comfortable appearance. Perfect for casual outings, gatherings and social occasions.",
        details: "This stylish women's dress combines an elegant appearance with comfortable fitting. The versatile design makes it suitable for different occasions including casual outings, gatherings and celebrations. It can also be paired with different accessories and footwear.",
        category: "Women",
        availability: "In Stock",
        features: ["Elegant design", "Comfortable fabric", "Stylish fitting", "Lightweight material", "Modern appearance", "Suitable for outings", "Easy to style"]
    },

    {
        id: "220",
        name: "Women's Casual Wear",
        price: 2200,
        image: "../../Images/fashion/women (2).jpg",
        description: "Comfortable women's casual wear designed for everyday use. Perfect for shopping, travel, outings and casual gatherings.",
        details: "This women's casual wear is designed to provide comfort and a relaxed appearance during everyday activities. The simple and attractive design makes it easy to combine with different accessories and footwear. It is suitable for shopping, travel and casual outings.",
        category: "Women",
        availability: "In Stock",
        features: ["Comfortable fabric", "Casual design", "Soft material", "Easy to wear", "Modern appearance", "Suitable for everyday use", "Easy to style"]
    },

    {
        id: "221",
        name: "Women's Fashion Outfit",
        price: 3200,
        image: "../../Images/fashion/women (3).jpg",
        description: "A fashionable women's outfit designed to create a modern and attractive look. Perfect for social gatherings, outings, celebrations and special occasions.",
        details: "This fashionable outfit is designed for women who prefer a modern and attractive appearance. The comfortable material allows easy movement while the stylish design creates an elegant look. It can be combined with accessories and footwear to create different fashion styles.",
        category: "Women",
        availability: "Out of Stock",
        features: ["Modern design", "Elegant appearance", "Comfortable material", "Stylish fitting", "Easy to accessorize", "Suitable for occasions", "Attractive finish"]
    },

    {
        id: "222",
        name: "Women's Elegant Wear",
        price: 3500,
        image: "../../Images/fashion/women (4).jpg",
        description: "Elegant women's wear designed for customers who prefer a sophisticated and fashionable appearance. Perfect for parties, celebrations and special occasions.",
        details: "This elegant women's wear is designed to provide a sophisticated and attractive appearance. Its comfortable fitting allows easy movement while the premium-looking design makes it suitable for parties, celebrations, gatherings and other special occasions.",
        category: "Women",
        availability: "In Stock",
        features: ["Elegant style", "Premium fabric", "Comfortable fitting", "Attractive appearance", "Stylish design", "Suitable for parties", "Suitable for special occasions"]
    },

    {
        id: "223",
        name: "Women's Trendy Outfit",
        price: 3000,
        image: "../../Images/fashion/women (5).jpg",
        description: "A trendy women's outfit designed according to modern fashion styles. Perfect for casual outings, gatherings, shopping and everyday fashion.",
        details: "This trendy women's outfit is designed to provide a modern and fashionable appearance while maintaining everyday comfort. Its versatile style makes it easy to pair with different accessories and footwear. It is suitable for casual outings, shopping, gatherings and regular wear.",
        category: "Women",
        availability: "In Stock",
        features: ["Trendy design", "Comfortable fit", "Modern appearance", "Soft fabric", "Easy to style", "Suitable for casual occasions", "Versatile design"]
    },

    {
        id: "224",
        name: "Women's Premium Fashion Wear",
        price: 4200,
        image: "../../Images/fashion/women (6).jpg",
        description: "Premium women's fashion wear designed for an elegant and sophisticated appearance. Perfect for celebrations, parties, gatherings and memorable occasions.",
        details: "This premium fashion wear is designed for customers who want an elegant and sophisticated appearance. The stylish design creates a fashionable look while the comfortable material allows easy movement. It is ideal for parties, celebrations, gatherings and other special occasions.",
        category: "Women",
        availability: "Out of Stock",
        features: ["Premium quality", "Elegant design", "Comfortable material", "Stylish appearance", "Attractive finish", "Suitable for parties", "Suitable for special occasions", "Easy to accessorize"]
    },
    // Home products: 301 - 312

    {
        id: "301",
        name: "Pressure Cooker",
        price: 2500,
        image: "../../Images/home/home (1).jpg",
        description: "A durable and easy-to-use pressure cooker designed for fast and convenient cooking. Perfect for preparing rice, vegetables, dal and other everyday meals.",
        details: "This pressure cooker is made for safe and efficient cooking. It provides even heating and helps reduce cooking time while retaining the natural taste of food. Its strong design makes it suitable for regular kitchen use.",
        category: "Kitchen Appliances",
        availability: "In Stock",
        features: ["Durable construction", "Fast cooking", "Even heating", "Easy to clean", "Safe locking system", "Suitable for daily use", "Easy to handle"]
    },

    {
        id: "302",
        name: "Steel Vegetable Maker",
        price: 1800,
        image: "../../Images/home/home (2).jpg",
        description: "A useful steel vegetable maker designed to make everyday vegetable preparation easier and more convenient.",
        details: "This steel vegetable maker is made with a strong and durable design for regular kitchen use. It helps in preparing vegetables quickly and conveniently while being easy to clean and maintain.",
        category: "Kitchen Appliances",
        availability: "In Stock",
        features: ["Strong steel body", "Easy to use", "Durable design", "Easy cleaning", "Compact size", "Convenient handling", "Suitable for daily use"]
    },

    {
        id: "303",
        name: "Wall Clock",
        price: 1200,
        image: "../../Images/home/home (3).jpg",
        description: "A stylish wall clock that adds a simple and attractive look to your home while helping you keep track of time.",
        details: "This wall clock features a clear and easy-to-read design that is suitable for bedrooms, living rooms, offices and other spaces. Its simple appearance makes it easy to match with different home interiors.",
        category: "Home Appliances",
        availability: "In Stock",
        features: ["Clear display", "Stylish design", "Easy to read", "Lightweight", "Simple installation", "Suitable for home", "Durable construction"]
    },

    {
        id: "304",
        name: "Ironing Machine",
        price: 2200,
        image: "../../Images/home/home (4).jpg",
        description: "An easy-to-use ironing machine designed to remove wrinkles and give clothes a neat and smooth appearance.",
        details: "This ironing machine provides convenient and efficient ironing for everyday clothes. Its comfortable design makes it easy to handle and suitable for regular household use.",
        category: "Home Appliances",
        availability: "Out of Stock",
        features: ["Fast heating", "Smooth ironing", "Comfortable handle", "Easy to use", "Lightweight design", "Suitable for daily use", "Compact design"]
    },

    {
        id: "305",
        name: "Freezer Refrigerator",
        price: 28000,
        image: "../../Images/home/home (5).jpg",
        description: "A spacious freezer refrigerator designed to keep food and beverages fresh and properly stored for longer periods.",
        details: "This freezer refrigerator provides useful storage space for everyday food items. It is designed with separate storage areas and efficient cooling to keep different types of food organized and fresh.",
        category: "Refrigerator",
        availability: "In Stock",
        features: ["Spacious storage", "Efficient cooling", "Freezer compartment", "Organized shelves", "Energy efficient design", "Durable construction", "Suitable for families"]
    },

    {
        id: "306",
        name: "Double Door Refrigerator",
        price: 32000,
        image: "../../Images/home/home (6).jpg",
        description: "A modern refrigerator with spacious storage designed to keep food, vegetables and beverages fresh and organized.",
        details: "This refrigerator provides convenient storage with separate sections for different food items. Its spacious interior and efficient cooling system make it suitable for everyday household use.",
        category: "Refrigerator",
        availability: "In Stock",
        features: ["Spacious interior", "Efficient cooling", "Multiple shelves", "Vegetable storage", "Freezer compartment", "Modern design", "Easy organization"]
    },

    {
        id: "307",
        name: "Front Load Washing Machine",
        price: 30000,
        image: "../../Images/home/home (7).jpg",
        description: "A modern front load washing machine designed to provide efficient and convenient washing for everyday clothes.",
        details: "This front load washing machine provides different washing options for various types of clothes. It is designed to clean clothes efficiently while offering convenient operation and good washing performance.",
        category: "Washing Machine",
        availability: "In Stock",
        features: ["Front load design", "Multiple wash programs", "Efficient washing", "Easy controls", "Low noise operation", "Suitable for daily use", "Modern design"]
    },

    {
        id: "308",
        name: "Front Load Washing Machine",
        price: 34000,
        image: "../../Images/home/home (8).jpg",
        description: "A powerful front load washing machine designed for convenient and effective cleaning of clothes with multiple washing options.",
        details: "This washing machine offers convenient washing programs for different types of clothes. Its front load design provides easy loading and efficient cleaning while making everyday laundry more manageable.",
        category: "Washing Machine",
        availability: "Out of Stock",
        features: ["Front load design", "Multiple wash programs", "Efficient cleaning", "Easy controls", "Large capacity", "Modern design", "Convenient operation"]
    },

    {
        id: "309",
        name: "Smart LED TV",
        price: 45000,
        image: "../../Images/home/home (9).jpg",
        description: "A large smart LED TV designed to provide an enjoyable viewing experience for movies, shows, sports and entertainment.",
        details: "This large LED TV provides a clear and enjoyable display for everyday entertainment. Its modern design fits well in living rooms and provides a convenient way to enjoy different types of content.",
        category: "Television",
        availability: "In Stock",
        features: ["Large screen", "Clear picture", "Smart features", "Modern design", "Good sound quality", "Easy controls", "Suitable for home entertainment"]
    },

    {
        id: "310",
        name: "Vegetable Chopper",
        price: 1500,
        image: "../../Images/home/home (10).jpg",
        description: "A convenient vegetable chopper designed to make chopping vegetables quick, simple and easy for everyday cooking.",
        details: "This vegetable chopper helps reduce the time required for preparing vegetables. Its compact design makes it easy to use, clean and store in the kitchen.",
        category: "Kitchen Appliances",
        availability: "In Stock",
        features: ["Quick chopping", "Easy to use", "Compact design", "Easy cleaning", "Lightweight", "Convenient storage", "Suitable for daily cooking"]
    },

    {
        id: "311",
        name: "Roti Dough Maker",
        price: 3500,
        image: "../../Images/home/home (11).jpg",
        description: "A convenient roti dough maker designed to make dough preparation easier and faster for everyday cooking.",
        details: "This roti dough maker helps prepare smooth and consistent dough with less manual effort. It is designed for convenient kitchen use and can make everyday roti preparation easier.",
        category: "Kitchen Appliances",
        availability: "Out of Stock",
        features: ["Easy dough preparation", "Time saving", "Easy to operate", "Compact design", "Easy cleaning", "Convenient handling", "Suitable for daily use"]
    },

    {
        id: "312",
        name: "Toaster",
        price: 2000,
        image: "../../Images/home/home (12).jpg",
        description: "A compact toaster designed to prepare crispy and evenly toasted bread quickly and conveniently.",
        details: "This toaster is designed for simple and convenient breakfast preparation. It provides even toasting and is easy to operate, making it suitable for everyday household use.",
        category: "Kitchen Appliances",
        availability: "In Stock",
        features: ["Even toasting", "Fast heating", "Easy controls", "Compact design", "Easy to clean", "Lightweight", "Suitable for daily use"]
    }

    // All 52 product objects are present in the complete file.
];

let cart = JSON.parse(localStorage.getItem("cart")) || [];

function saveCart() {
    localStorage.setItem("cart", JSON.stringify(cart));
}

function updateCartCount() {
    let cartCount = document.querySelector(".cart-count");

    if (cartCount == null) {
        return;
    }

    let total = 0;

    for (let i = 0; i < cart.length; i++) {
        total += cart[i].quantity;
    }

    cartCount.textContent = total;
}

function addToCart(product, quantity = 1) {
    if (product == null) {
        alert("Product not found.");
        return;
    }

    if (product.availability == "Out of Stock") {
        alert("Sorry, this product is out of stock.");
        return;
    }

    let found = false;

    for (let i = 0; i < cart.length; i++) {
        if (cart[i].id == product.id) {
            cart[i].quantity += quantity;
            found = true;
            break;
        }
    }

    if (found == false) {
        cart.push({
            id: product.id,
            name: product.name,
            price: product.price,
            image: product.image,
            category: product.category,
            quantity: quantity
        });
    }

    saveCart();
    updateCartCount();
    alert(product.name + " added to cart!");
}

function findProduct(id) {
    for (let i = 0; i < products.length; i++) {
        if (products[i].id == id) {
            return products[i];
        }
    }

    return null;
}

updateCartCount();

*/