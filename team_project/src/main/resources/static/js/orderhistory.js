const orders = [
    {
        orderName: '하수빈',
        orderAddr: '서울 강북구 미아동',
        orderPName: '운동화',
        orderPType: '신발',
        orderPColor: '검정',
        orderPPrice: '50,000원',
        orderOdStart: '2024-10-01',
        orderOdEnd: '2024-10-05'
    },
    {
        orderName: '이순신',
        orderAddr: '서울 종로구',
        orderPName: '티셔츠',
        orderPType: '의류',
        orderPColor: '흰색',
        orderPPrice: '20,000원',
        orderOdStart: '2024-10-02',
        orderOdEnd: '2024-10-06'
    }
];

function displayOrders(orders) {
    const orderList = document.getElementById('orderList');
    orderList.innerHTML = ''; // 기존 내용 초기화

    orders.forEach(order => {
        const listItem = document.createElement('li');

        listItem.innerHTML = `
            <div class="order_name">${order.orderName}</div>
            <div class="order_addr">${order.orderAddr}</div>
            <div class="order_pname">${order.orderPName}</div>
            <div class="order_ptype">${order.orderPType}</div>
            <div class="order_pcolor">${order.orderPColor}</div>
            <div class="order_pprice">${order.orderPPrice}</div>
            <div class="order_odstart">${order.orderOdStart}</div>
            <div class="order_odend">${order.orderOdEnd}</div>
        `;

        orderList.appendChild(listItem);
    });
}

displayOrders(orders);