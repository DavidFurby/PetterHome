<template>
  <nb-container>
    <nb-header><nb-title>Change Password</nb-title> </nb-header>
    <nb-content>
      <nb-form>
        <nb-item>
          <nb-input placeholder="Username" />
        </nb-item>
        <nb-item last>
          <nb-input placeholder="Old Password" />
        </nb-item>
        <nb-item last>
          <nb-input placeholder="New Password" />
        </nb-item>
        <nb-item last>
          <nb-input placeholder="Confirm Password" />
        </nb-item>
        <nb-button><nb-text>Change</nb-text></nb-button>
      </nb-form>
    </nb-content>
  </nb-container>
</template>