import React, { useState } from 'react'
import { useHistory } from 'react-router'

const BoxAddOrEdit = (props) => {

    const history = useHistory()
    const boxToEdit = props.boxToEditProps

    const [boxDetails, setBoxDetails] = useState({
        name: "",
        basePrice: 0,
        content: "",
        description: "",
    })

    const handleSubmitAdd = e => {
        e.preventDefault();
        if(boxDetails.name.trim() && boxDetails.content.trim() && boxDetails.description.trim()){
        props.addBoxInBEProps(boxDetails);
        history.push("/boxes")
        }
        else{
            alert("Please fill in all fields")
        }
    }

    const onChangeAdd = e => {
        setBoxDetails({
            ...boxDetails,
            [e.target.name]: e.target.value,
        })
    }

    const handleEditBox = () => {

    }

    const onChangeEdit = e => {

    }

    if (boxToEdit == null) {
        return (
            <form onSubmit={handleSubmitAdd}>
                <h1>Add a new box</h1>
                <label>
                    Name:
                    <input
                        type="text"
                        name="name"
                        placeholder="Insert a name"
                        value={boxDetails.name}
                        onChange={onChangeAdd} />
                </label>
                <br />
                <label>
                    Base price:
                    <input
                        type="text"
                        name="basePrice"
                        placeholder="Insert a base price"
                        value={boxDetails.basePrice}
                        onChange={onChangeAdd} />
                </label>
                <br />
                <label>
                    Content:
                    <input
                        type="text"
                        name="content"
                        placeholder="Insert the content"
                        value={boxDetails.content}
                        onChange={onChangeAdd} />
                </label>
                <br />
                <label>
                    Description:
                    <input
                        type="text"
                        name="description"
                        placeholder="Insert a description"
                        value={boxDetails.description}
                        onChange={onChangeAdd} />
                </label>
                <br />
                <input type="submit" value="Submit" />
            </form>
        )
    }

    return (
        <form onSubmit={handleEditBox}>
            <h1>Edit an existing box</h1>
            <label>
                Name:
                <input
                    type="text"
                    id="boxName"
                    placeholder="Insert a name"
                    value={boxToEdit.name}
                    onChangeEdit={onChangeEdit} />
            </label>
            <br />
            <label>
                Base price:
                <input type="text" id="boxBasePrice" placeholder="Insert a base price" value={boxToEdit.id} />
            </label>
            <br />
            <label>
                Content:
                <input type="text" id="boxContent" placeholder="Insert the content" value={boxToEdit.content} />
            </label>
            <br />
            <label>
                Description:
                <input type="text" id="boxDescription" placeholder="Insert a description" value={boxToEdit.description} />
            </label>
            <br />
            <input type="submit" value="Submit" />
        </form>
    )
}

export default BoxAddOrEdit