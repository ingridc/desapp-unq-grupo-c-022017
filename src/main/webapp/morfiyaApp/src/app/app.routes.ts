import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserComponent } from './components/users/user.component';
import { EditUserComponent } from './components/editUser/editUser.component';
import { CheckBalanceComponent } from './components/checkBalance/checkBalance.component';

export const appRoutes: Routes = [
    { path: '', redirectTo:'/home', pathMatch:'full'},
    { path: 'home', component: HomeComponent},
    { path: 'users', component: UserComponent,
    children: [
      {
        path: 'edit',
        component: EditUserComponent
      },
      {
        path: 'balance',
        component: CheckBalanceComponent
      },
    ]},
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];