// app/common/page.tsx
"use client";

import React from "react";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { useRouter } from "next/navigation";

export default function CommonPage() {
  const { token } = useSelector((state: RootState) => state.auth);
  const router = useRouter();

  if (!token) {
    if (typeof window !== "undefined") {
      router.push("/login");
    }
    return null;
  }

  return <h2 style={{ margin: "2rem" }}>Hello, welcome to the Common Page!</h2>;
}
