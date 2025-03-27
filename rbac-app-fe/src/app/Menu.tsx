// app/Menu.tsx
"use client";

import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "./redux/store";
import { logout } from "./redux/authSlice";
import Link from "next/link";

export default function Menu() {
  const { token, user } = useSelector((state: RootState) => state.auth);
  const dispatch = useDispatch();

  const handleLogout = () => {
    dispatch(logout());
  };
  const trimmed = user?.aud?.slice(1, -1) ?? "";
  const arr = trimmed ? trimmed.split(",").map((item) => item.trim()) : [];

  const isAdmin = arr.includes("ADMIN");
  const isUser = arr.includes("USER");

  return (
    <nav style={{ padding: "1rem", borderBottom: "1px solid #ccc" }}>
      <ul style={{ display: "flex", gap: "1rem", listStyle: "none" }}>
        {!token && (
          <li>
            <Link href="/login">Login</Link>
          </li>
        )}
        {token && (
          <>
            <li>
              <Link href="/common">Common Page</Link>
            </li>
            {isAdmin && (
              <li>
                <Link href="/admin">Admin Page</Link>
              </li>
            )}
            {(isUser || isAdmin) && (
              <li>
                <Link href="/user">User Page</Link>
              </li>
            )}
            <li>
              <button onClick={handleLogout}>Logout</button>
            </li>
          </>
        )}
      </ul>
    </nav>
  );
}
