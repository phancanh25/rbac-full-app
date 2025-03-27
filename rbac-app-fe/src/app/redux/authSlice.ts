// app/redux/authSlice.ts
import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { jwtDecode } from "jwt-decode";

interface DecodedToken {
  sub: string; // username
  roles?: string[]; // roles array (e.g., ['ADMIN', 'USER'])
  exp?: number; // expiration
  aud?: string;
  // ... any additional fields
}

interface AuthState {
  token: string | null;
  user: DecodedToken | null;
}

const initialState: AuthState = {
  token: null,
  user: null,
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    loginSuccess: (state, action: PayloadAction<string>) => {
      state.token = action.payload;
      state.user = jwtDecode<DecodedToken>(action.payload);
      localStorage.setItem("token", action.payload);
    },
    logout: (state) => {
      state.token = null;
      state.user = null;
      localStorage.removeItem("token");
    },
    rehydrate: (state, action: PayloadAction<string>) => {
      state.token = action.payload;
      state.user = jwtDecode<DecodedToken>(action.payload);
    },
  },
});

export const { loginSuccess, logout, rehydrate } = authSlice.actions;
export default authSlice.reducer;
