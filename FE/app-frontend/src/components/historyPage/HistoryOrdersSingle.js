import styles from "./HistoryOrdersSingle.module.css"

const HistoryOrdersSingle = (props) => {


    const order = props.order
    console.log(order)
    return (
        <li className={styles.li}>
            {order.shipped === true ?
                <p>This order has been shipped and should be delivered on: {order.deliveryDate}</p>
                :
                <p>This order needs to be send and should be delivered on: {order.deliveryDate}</p>
            }

        </li>
    )
}

export default HistoryOrdersSingle