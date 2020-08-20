import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class ListEdit extends Component {

  emptyItem = {
    name: '',
    dateAdded: '',
    priority: 0,
    completed: false,
    notes: ''
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      const group = await (await fetch(`/api/group/${this.props.match.params.id}`)).json();
      this.setState({item: group});
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/api/group', {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/groups');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit item' : 'Add item'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={item.name || ''}
                   onChange={this.handleChange} autoComplete="name"/>
          </FormGroup>
          <FormGroup>
            <Label for="dateAdded">Date Added</Label>
            <Input type="text" name="dateAdded" id="dateAdded" value={item.dateAdded || ''}
                   onChange={this.handleChange} autoComplete="address-level1"/>
          </FormGroup>
          <FormGroup>
            <Label for="priority">Priority</Label>
            <Input type="number" name="priority" id="priority" value={item.priority || ''}
                   onChange={this.handleChange} autoComplete="address-level1"/>
          </FormGroup>
          <FormGroup>
              <Label for="notes">Notes</Label>
              <Input type="text" name="notes" id="notes" value={item.notes || ''}
                     onChange={this.handleChange} autoComplete="address-level1"/>
            </FormGroup>  
         
          
          <FormGroup className="col-md-4 mb-3">
              <Input type="checkbox" name="completed" id="completed" defaultChecked={true}
                     onChange={this.handleChange} autoComplete="address-level1"/>
                     <Label for="completed">Completed?</Label>
            </FormGroup>
            <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/groups">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

//Needed to expose this.props.history so we can go back to ToDoList after adding/saving group 
export default withRouter(ListEdit);