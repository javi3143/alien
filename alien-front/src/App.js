import React, { Component } from 'react';
import axios from 'axios';
import {Table, Button, ModalHeader, Modal, ModalFooter, ModalBody, Label, Input, FormGroup} from 'reactstrap';

class App extends Component {
  state = {
    aliens: [],
    newAlienData: {
      name:'',
      type:'',
      planet:'',
      parent:''

    },
    editAlienData: {
      name:'',
      type:'',
      planet:'',
      parent:''
    },
    newAlienModal: false,
    editAlienModal: false
  }
  componentWillMount() {
    this._refreshAliens()
  }

  addAlien(){
    axios.post('http://localhost:8080/alien', this.state.newAlienData).then((response) => {
      let {aliens} = this.state;

      aliens.push(response.data);

      this.setState({aliens, newAlienModal: false, newAlienData: {
        title:'',
        type: '',
        planet: '',
        parent: ''
      }});
    });
  }
  updateAlien() {
    axios.put('http://localhost:8080/alien', this.state.editAlienData).then((response) => {
      this._refreshAliens();

      this.setState({
        editAlienModal: false, editAlienData: {name: '', type: '', planet: '', parent: ''}
      });
    });
  }
  editAlien(name, type, planet, parent) {
    this.setState( {
      editAlienData: { name, type, planet, parent}, editAlienModal: ! this.state.editAlienModal
    });
  }
  _refreshAliens () {
    axios.get('http://localhost:8080/aliens').then((response) => {
      this.setState({
        aliens: response.data
      })
    });
  }
  deleteAlien (name) {
      axios.delete('http://localhost:8080/alien/' + name).then((response) => {
        this._refreshAliens();
      });
  }
  toggleNewAlienModal() {
    this.setState({
      newAlienModal: ! this.state.newAlienModal
    })
    // this.state.newAlienModal  = true;
  }
  toggleEditAlienModal() {
    this.setState({
      editAlienModal: ! this.state.editAlienModal
    })
    // this.state.newAlienModal  = true;
  }
  render() {
    let aliens = this.state.aliens.map((alien) => {
    return (
      <tr key={alien.name}>
        <td>{alien.name}</td>
        <td>{alien.type}</td>
        <td>{alien.planet}</td>
        <td>{alien.parent}</td>
        <td>
          <Button color="success" size="sm" className="mr-2" onClick={this.editAlien.bind(this, alien.name, alien.type, alien.planet, alien.parent)}>Edit</Button>
          <Button color="danger" size="sm" onClick={this.deleteAlien.bind(this, alien.name)}>Delete</Button>
        </td>
      </tr>
      )
    });
    return (
      <div className="App container">

      <h1> Alien race App</h1>

      <Button className="my-3" color="primary" onClick={this.toggleNewAlienModal.bind(this)}>Add a new alien</Button>

      <Modal isOpen={this.state.newAlienModal} toggle={this.toggleNewAlienModal.bind(this)}>
        <ModalHeader toggle={this.toggleNewAlienModal.bind(this)}>Add a new alien</ModalHeader>
        <ModalBody>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input id="name" value={this.state.newAlienData.name} onChange={(e) => {
              let {newAlienData} = this.state
              newAlienData.name = e.target.value;
              this.setState({newAlienData});
            }}/>
          </FormGroup>
          <FormGroup>
            <Label for="type">Type</Label>
            <Input id="type" value={this.state.newAlienData.type} onChange={(e) => {
              let {newAlienData} = this.state
              newAlienData.type = e.target.value;
              this.setState({newAlienData});
            }}/>
          </FormGroup>
          <FormGroup>
            <Label for="planet">Birth Planet</Label>
            <Input id="planet" value={this.state.newAlienData.planet} onChange={(e) => {
              let {newAlienData} = this.state
              newAlienData.planet = e.target.value;
              this.setState({newAlienData});
            }}/>
          </FormGroup>
          <FormGroup>
            <Label for="parent">Parent</Label>
            <Input id="parent" value={this.state.newAlienData.parent} onChange={(e) => {
              let {newAlienData} = this.state
              newAlienData.parent = e.target.value;
              this.setState({newAlienData});
            }}/>
          </FormGroup>
        </ModalBody>
        <ModalFooter>
          <Button color="primary" onClick={this.addAlien.bind(this)}>Add Alien</Button>{' '}
          <Button color="secondary" onClick={this.toggleNewAlienModal.bind(this)}>Cancel</Button>
        </ModalFooter>
      </Modal>

      <Modal isOpen={this.state.editAlienModal} toggle={this.toggleEditAlienModal.bind(this)}>
        <ModalHeader toggle={this.toggleEditAlienModal.bind(this)}>Edit an alien</ModalHeader>
        <ModalBody>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input id="name" value={this.state.editAlienData.name} onChange={(e) => {
              let {editAlienData} = this.state
              editAlienData.name = e.target.value;
              this.setState({editAlienData});
            }}/>
          </FormGroup>
          <FormGroup>
            <Label for="type">Type</Label>
            <Input id="type" value={this.state.editAlienData.type} onChange={(e) => {
              let {editAlienData} = this.state
              editAlienData.type = e.target.value;
              this.setState({editAlienData});
            }}/>
          </FormGroup>
          <FormGroup>
            <Label for="planet">Birth Planet</Label>
            <Input id="planet" value={this.state.editAlienData.planet} onChange={(e) => {
              let {editAlienData} = this.state
              editAlienData.planet = e.target.value;
              this.setState({editAlienData});
            }}/>
          </FormGroup>
          <FormGroup>
            <Label for="parent">Parent</Label>
            <Input id="parent" value={this.state.editAlienData.parent} onChange={(e) => {
              let {editAlienData} = this.state
              editAlienData.parent = e.target.value;
              this.setState({editAlienData});
            }}/>
          </FormGroup>
        </ModalBody>
        <ModalFooter>
          <Button color="primary" onClick={this.updateAlien.bind(this)}>Edit Alien</Button>{' '}
          <Button color="secondary" onClick={this.toggleEditAlienModal.bind(this)}>Cancel</Button>
        </ModalFooter>
      </Modal>

        <Table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Type</th>
              <th>Planet</th>
              <th>Parent</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {aliens}
          </tbody>
        </Table>
      </div>
    );
  }
}
export default App;
