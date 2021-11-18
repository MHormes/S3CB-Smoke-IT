import React, { useState } from 'react'
import { useHistory } from 'react-router'
import axios from 'axios'
import * as urls from "./../../URL";

//component that will show during the box edit process
const BoxEdit = (props) => {

    const history = useHistory()
    const boxToEdit = props.boxToEditProps

    const jwtToken = props.jwtTokenProps
    const [boxDetails, setBoxDetails] = useState(
        {
            name: boxToEdit.name,
            basePrice: boxToEdit.basePrice,
            content: boxToEdit.content,
            description: boxToEdit.description
        }
    )

    //State for picture upload
    const [selectedFile, setSelectedFile] = useState("");

    //method to call edit endpoint
    const editBoxInBE = (id, file) => {
        file.append("id", id);
        file.append("name", boxDetails.name);
        file.append("basePrice", boxDetails.basePrice)
        file.append("content", boxDetails.content)
        file.append("description", boxDetails.description)

        axios.put(urls.baseURL + urls.boxesEditURL, file, {
            headers: {
                'Authorization': jwtToken
            }
        }
        ).then(res => {
            if (res.status === 200) {
                console.log("Update successfull")
            }
        }).catch(err => {
            if(err.status == null){
                alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
            }
            else{
                alert(err.status)
            }
        })
    }



    const onChange = e => {
        setBoxDetails({
            ...boxDetails,
            [e.target.name]: e.target.value,
        })
    }

    const handleSubmit = e => {
        e.preventDefault();

        let formData = new FormData()
        if(selectedFile != null){
            formData.append(
                'imageFile',
                selectedFile
            )
        }else{
            formData.append(
                'imageFile',
                ""
            )
        }
       

        if (boxDetails.name.trim() && boxDetails.content.trim() && boxDetails.description.trim()) {
            editBoxInBE(boxToEdit.id, formData);

            //clear the state
            setBoxDetails({
                name: "",
                basePrice: 0,
                content: "",
                description: "",
            })

            //clear file state
            setSelectedFile(null)

            //redirect to boxes page (refreshes)
            history.push("/boxes")
        }
        else {
            alert("Please fill in all fields")
        }
    }

    const onFilechange = e => {
        setSelectedFile(e.target.files[0])
    }
    return (
        <form onSubmit={handleSubmit}>
            <h1>Edit an existing box</h1>
            <label>
                Upload a new picture (overrides the old picture):
                <br />
                <input
                    type="file"
                    accept="image/png, image/jpg, image/jpeg"
                    onChange={onFilechange}
                />
            </label>
            <br />
            <label>
                Name:
                <input
                    type="text"
                    name="name"
                    placeholder="Insert a name"
                    value={boxDetails.name}
                    onChange={onChange} 
                    required/>
            </label>
            <br />
            <label>
                Base price:
                <br />
                <input
                    type="number"
                    name="basePrice"
                    placeholder="Insert a base price"
                    value={boxDetails.basePrice}
                    onChange={onChange} 
                    required/>
            </label>
            <br />
            <label>
                Content:
                <input
                    type="text"
                    name="content"
                    placeholder="Items separated with commas (,) will be listed below each other on the box page"
                    value={boxDetails.content}
                    onChange={onChange} 
                    required/>
            </label>
            <br />
            <label>
                Description:
                <input
                    type="text"
                    name="description"
                    placeholder="Insert a description"
                    value={boxDetails.description}
                    onChange={onChange} 
                    required/>
            </label>
            <br />
            <input type="submit" value="Submit" />
        </form>
    )
}

export default BoxEdit