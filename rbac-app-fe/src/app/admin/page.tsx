// app/admin/page.tsx
"use client";

import React from "react";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { useRouter } from "next/navigation";

export default function AdminPage() {
  const { token, user } = useSelector((state: RootState) => state.auth);
  const router = useRouter();

  const trimmed = user?.aud?.slice(1, -1) ?? "";
  const arr = trimmed ? trimmed.split(",").map((item) => item.trim()) : [];

  if (!token || !arr.includes("ADMIN")) {
    // Redirect or render an error message
    if (typeof window !== "undefined") {
      router.push("/login");
    }
    return null;
  }

  return <h2 style={{ margin: "2rem" }}>Hello Admin!</h2>;
}
