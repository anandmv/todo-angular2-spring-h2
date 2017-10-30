import { Component, OnInit } from '@angular/core';

import { Title }     from '@angular/platform-browser';

import { TdLoadingService, TdDigitsPipe } from '@covalent/core';

import { UserService, IUser } from '../users';

import { TodoService } from '../../services';

import { multi } from './data';

@Component({
  selector: 'qs-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  viewProviders: [ TodoService ],
})
export class DashboardComponent implements OnInit {

  displayCompleted: Boolean = true;
  displayPending: Boolean = true;

  todos: Object[];

  title: String;

  constructor(private _titleService: Title,
              private _todoService: TodoService,
              private _loadingService: TdLoadingService) {
  }

  ngOnInit(): void {
    this._titleService.setTitle( 'Todo App' );
    this.getTodoList();
  }

  getTodoList(){
    this._loadingService.register('todo.load');
    this._todoService.query().subscribe((todos: Object[]) => {
      this.todos = todos;
      setTimeout(() => {
        this._loadingService.resolve('todo.load');
      }, 750);
    }, (error: Error) => {
          this._loadingService.resolve('todo.load');
        console.log(error)
      });
  }

  filterTodo(paramName:String){
    if((this.displayCompleted==true&&this.displayPending==true) ||
      (this.displayCompleted==false&&this.displayPending==false)){
      this.getTodoList();
    }
    else{
      let status = this.displayCompleted ?"true":"false";
      this._todoService.get("list/completed/"+status).subscribe((todos: Object[]) => {
        this.todos = todos;
        setTimeout(() => {
          this._loadingService.resolve('todo.load');
        }, 750);
      }, (error: Error) => {
            this._loadingService.resolve('todo.load');
          console.log(error)
        });
    }
  }

  save(){
    this._loadingService.register('todo.load');
    this._todoService.create({'title':this.title,'completed':false}).subscribe((todo:Object) =>{
      this.title = "";
      this.todos.push(todo);
      setTimeout(() => {
        this._loadingService.resolve('todo.load');
      }, 750);
    }, (error: Error) => {
          this._loadingService.resolve('todo.load');
        console.log(error)
      });
  }

  update(todo:Object){
    todo['completed'] = !todo['completed']
    this._loadingService.register('todo.load');
    this._todoService.update(todo['id'],todo).subscribe((todo:Object) =>{
      setTimeout(() => {
        this._loadingService.resolve('todo.load');
      }, 750);
    }, (error: Error) => {
          this._loadingService.resolve('todo.load');
        console.log(error)
      });
  }

  delete(id){
    this._loadingService.register('todo.load');
    this._todoService.delete(id).subscribe((todo:Object) =>{
      setTimeout(() => {
        this._loadingService.resolve('todo.load');
      }, 750);
    }, (error: Error) => {
          this._loadingService.resolve('todo.load');
        console.log(error)
      });
  }

}
