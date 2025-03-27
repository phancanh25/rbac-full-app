// app/Providers.tsx
"use client";

import React, { useEffect } from "react";
import { Provider } from "react-redux";
import { store } from "./redux/store";
import { rehydrate } from "./redux/authSlice";

export default function Providers({ children }: { children: React.ReactNode }) {
  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken) {
      store.dispatch(rehydrate(storedToken));
    }
  }, []);

  return <Provider store={store}>{children}</Provider>;
}
